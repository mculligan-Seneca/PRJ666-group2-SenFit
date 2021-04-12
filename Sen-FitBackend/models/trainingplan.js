'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class TrainingPlan extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({FitnessPortfolio,Trainer,Member}) {
      // define association here
      this.belongsTo(FitnessPortfolio,
        {foreignKey: 'fitnessPortfolioId', as: 'fitnessPortfolios'});
      this.belongsTo(Trainer,{foreignKey:'trainerId', as:'trainer'});
      this.belongsTo(Member,{foreignKey:'member_id', as: 'member'});
    }
  };
  TrainingPlan.init({
    startDate: DataTypes.DATE,
    fitnessPortfolioId: DataTypes.INTEGER,
    trainerId: DataTypes.INTEGER,
    member_id: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'TrainingPlan',
  });
  return TrainingPlan;
};