'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class GymClass extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({FitnessClass,Trainer,GymLocation,Member,GymClass}) {
      // define association here
        this.belongsTo(FitnessClass,{foreignKey: 'fitnessClassId',as: 'fitnessClass'});
        this.belongsTo(Trainer,{foreignKey: 'trainerId', as:'trainer'});
        this.belongsTo(GymLocation,{foreignKey: 'gymLocationId',as:'gymLocation'});
        this.belongsToMany(Member,{through: 'Member_GymClass'});//many to many enrollment
    }
    toJSON(){
      return {...this.get(),id: undefined,trainerId:undefined,fitnessClassId:undefined,
        gymLocationId:undefined,TrainerId:undefined};
    }
  };
  GymClass.init({
    class_date: DataTypes.DATEONLY,
    start_time: DataTypes.TIME,
    end_time: DataTypes.TIME,
    trainerId: DataTypes.INTEGER,
    fitnessClassId: DataTypes.INTEGER,
    gymLocationId: DataTypes.INTEGER,
    enrolled: DataTypes.BOOLEAN
  }, {
    sequelize,
    modelName: 'GymClass',
    freezeTableName:true
  });
  return GymClass;
};