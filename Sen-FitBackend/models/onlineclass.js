'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class OnlineClass extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({FitnessClass,Trainer,Member}) {
      // define association here
      this.belongsTo(FitnessClass,{foreignKey: 'FitnessClassId',as: 'fitnessClass'});
      this.belongsTo(Trainer,{foreignKey: 'trainerId', as:'trainer'});
      this.belongsToMany(Member,{through: 'Member_OnlineClass'});//many to many enrollment

    }
  };
  OnlineClass.init({
    classDate: DataTypes.DATEONLY,
    start_time: DataTypes.DATE,
    end_time: DataTypes.DATE,
    FitnessClassId: DataTypes.INTEGER,
    trainerId: DataTypes.INTEGER,
    enrolled: DataTypes.BOOLEAN
  }, {
    sequelize,
    modelName: 'OnlineClass',
  });
  return OnlineClass;
};