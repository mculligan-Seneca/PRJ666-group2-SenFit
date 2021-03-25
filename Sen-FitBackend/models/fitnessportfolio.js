'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class FitnessPortfolio extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({Member,FitnessResult}) {
      // define association here
      this.belongsTo(Member,{foreignKey: 'member_id', as: 'member'});
      this.hasMany(FitnessResult,{foreignKey:'fitnessPortfolioId'});
    }
  };
  FitnessPortfolio.init({
    height: DataTypes.INTEGER,
    weight: DataTypes.INTEGER,
    date_created: DataTypes.DATE,
    health_concerns: DataTypes.TEXT,
    member_id:{
        type: DataTypes.INTEGER,
        allowNull:false
      }
  }, {
    sequelize,
    modelName: 'FitnessPortfolio',
  });
  return FitnessPortfolio;
};