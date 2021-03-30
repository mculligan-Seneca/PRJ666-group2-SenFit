'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
      return queryInterface.changeColumn('GymClass','class_date',{
        type: Sequelize.DATEONLY
      });
    /**
     * Add altering commands here.
     *
     * Example:
     * await queryInterface.createTable('users', { id: Sequelize.INTEGER });
     */
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.changeColumn('GymClass','class_date',{
      type: Sequelize.DATE
    });
    /**
     * Add reverting commands here.
     *
     * Example:
     * await queryInterface.dropTable('users');
     */
  }
};
