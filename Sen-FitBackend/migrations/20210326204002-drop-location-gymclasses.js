'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
   // return queryInterface.removeColumn('GymClass','gymLocationId');
    /**
     * Add altering commands here.
     *
     * Example:
     * await queryInterface.createTable('users', { id: Sequelize.INTEGER });
     */
  },

  down: async (queryInterface, Sequelize) => {
   /*   return queryInterface.addColumn('GymClass','gymLocationId',{
            type: Sequelize.INTEGER,
            references: 'GymLocations',
            referencesKey: 'id'
        });
      
    /**
     * Add reverting commands here.
     *
     * Example:
     * await queryInterface.dropTable('users');
     */
  }
};
