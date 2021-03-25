'use strict';
module.exports = {
  up: async (queryInterface, Sequelize) => {
    await queryInterface.createTable('FitnessResults', {
      fitnessPortfolioId: {
        allowNull: false,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      exerciseId: {
        allowNull: false,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      reps_num: {
        type: Sequelize.INTEGER
      },
      beatsPM: {
        type: Sequelize.INTEGER
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });
  },
  down: async (queryInterface, Sequelize) => {
    await queryInterface.dropTable('FitnessResults');
  }
};