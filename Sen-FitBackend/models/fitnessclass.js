'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class FitnessClass extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({GymClass,OnlineClass}) {
      // define association here
      this.hasMany(GymClass,{foreignKey:'fitnessClassId'});
      this.hasMany(OnlineClass,{foreignKey:'FitnessClassId'});
    }

  };
  FitnessClass.init({
    className: DataTypes.STRING,
    description: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'FitnessClass',
  });
  return FitnessClass;
};