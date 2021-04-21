'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class GymLocation extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({Trainer,GymClass}) {
      // define association here
     
      this.hasMany(Trainer,{foreignKey: 'gymLocationId'});
      this.hasMany(GymClass,{foreignKey: 'gymLocationId'});
    }
 
  };
  GymLocation.init({
    postal_code: DataTypes.STRING,
    street_address: DataTypes.STRING,
    province:DataTypes.STRING
  }, {
    sequelize,
    modelName: 'GymLocation',
  });
  return GymLocation;
};