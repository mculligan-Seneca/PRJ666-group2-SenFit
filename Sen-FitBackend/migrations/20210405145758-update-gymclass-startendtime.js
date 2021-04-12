'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return Promise.all([queryInterface.removeColumn('GymClass','start_time'),
    queryInterface.removeColumn('GymClass','end_time'),
      queryInterface.addColumn('GymClass','start_time',
    {
      type: Sequelize.DATE
    }),queryInterface.addColumn('GymClass','end_time',
    {
      type: Sequelize.DATE
    })
  ]);
    /**
     * Add altering commands here.
     *
     * Example:
     * await queryInterface.createTable('users', { id: Sequelize.INTEGER });
     */
  },

  down: async (queryInterface, Sequelize) => {
    
    /**
     * Add reverting commands here.
     *
     * Example:
     * await queryInterface.dropTable('users');
     */
  }
};
