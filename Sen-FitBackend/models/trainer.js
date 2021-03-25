'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Trainer extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({GymClass}) {
      // define association here
      this.hasMany(GymClass,{foreignKey:'trainerId',as: 'gymClasses'});
    }
    toJSON(){
      return {...this.get(),id:undefined};
    }
  };
  Trainer.init({
    first_name: DataTypes.STRING,
    last_name: DataTypes.STRING,
    post_code: DataTypes.STRING,
    email: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'Trainer',
    freezeTableName: true
  });
  return Trainer;
};