'use strict';
const {
  Model, BOOLEAN
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class GymClass extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({FitnessClass,Trainer,GymLocation,Member}) {
      // define association here
        this.belongsTo(FitnessClass,{foreignKey: 'fitnessClassId',as: 'fitnessClass'});
        this.belongsTo(Trainer,{foreignKey: 'trainerId', as:'trainer'});
        this.belongsTo(GymLocation,{foreignKey: 'gymLocationId',as:'gymLocation'});
        this.belongsToMany(Member,{through: 'Member_GymClass'});//many to many enrollment
    }
   
  };
  GymClass.init({
    class_date: DataTypes.DATEONLY,
    start_time: DataTypes.DATE,
    end_time: DataTypes.DATE,
    trainerId: DataTypes.INTEGER,
    fitnessClassId: DataTypes.INTEGER,
    gymLocationId: DataTypes.INTEGER,
    enrolled: DataTypes.BOOLEAN,
    isFull: {
      type: DataTypes.BOOLEAN,
      defaultValue:false
    }
  }, {
    sequelize,
    modelName: 'GymClass',
    freezeTableName:true
  });
  return GymClass;
};