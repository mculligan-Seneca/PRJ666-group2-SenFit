'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
/*    return Promise.all([
  /*    queryInterface.addColumn('Trainer','gymLocationId',{
          type: Sequelize.INTEGER,
          allowNull:true,
          foreignKey:true
      }),
      queryInterface.removeColumn('Trainer','post_code'),
    /*  queryInterface.addConstraint('Trainer',{
        fields:['gymLocationId'],
        type: 'foreign key',
        name: 'gym_locationId_trainer_gymLocationFK',
        references:{
          table: 'GymLocations',
          field: 'id'
        }
        
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
   /*   return Promise.all([
        queryInterface.removeConstraint('Trainer',
        'gym_locationId_trainer_gymLocationFK'),
        queryInterface.addColumn('Trainer','post_code',{
          type: Sequelize.STRING
        }),
        queryInterface.removeColumn('Trainer','gymLocationId')
      ]);
    /**
     * Add reverting commands here.
     *
     * Example:
     * await queryInterface.dropTable('users');
     */
  }
};
