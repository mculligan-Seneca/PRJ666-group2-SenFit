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
    static associate({GymClass}) {
      // define association here
      this.hasMany(GymClass,{foreignKey:'gymLocationId'})
    }
    toJSON(){
      return {...this.get(),id: undefined};
    }
  };
  GymLocation.init({
    postal_code: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'GymLocation',
  });
  return GymLocation;
};