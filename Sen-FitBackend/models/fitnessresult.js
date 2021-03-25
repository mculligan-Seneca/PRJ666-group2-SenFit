'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class FitnessResult extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({Exercise,FitnessPortfolio}) {
      // define association here
      this.belongsTo(Exercise,{foreignKey:'exerciseId',as: 'exercise'});
      this.belongsTo(FitnessPortfolio,{foreignKey:'fitnessPortfolioId',as: 'fitnessPortfolio'});

    }
  };
  FitnessResult.init({
    fitnessPortfolioId: {
      allowNull: false,
      primaryKey: true,
      type: DataTypes.INTEGER
    },
    exerciseId: {
      allowNull: false,
      primaryKey: true,
      type: DataTypes.INTEGER
    },
    reps_num: DataTypes.INTEGER,
    beatsPM: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'FitnessResult',
  });
  return FitnessResult;
};