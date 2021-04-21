'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return Promise.all([queryInterface.addColumn('GymLocations',
    'street_address',
    {
      type:Sequelize.STRING
    }),
  queryInterface.addColumn('GymLocations','province',{
    type:Sequelize.STRING
    })]);
    /**
     * Add altering commands here.
     *
     * Example:
     * await queryInterface.createTable('users', { id: Sequelize.INTEGER });
     */
  },

  down: async (queryInterface, Sequelize) => {
    return Promise.all([queryInterface.removeColumn('GymLocations','street_address'),
      queryInterface.removeColumn('GymLocations','province')]);
    /**
     * Add reverting commands here.
     *
     * Example:
     * await queryInterface.dropTable('users');
     */
  }
};
