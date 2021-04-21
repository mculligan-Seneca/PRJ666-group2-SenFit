'use strict';
module.exports = {
  up: async (queryInterface, Sequelize) => {
  /*  await queryInterface.createTable('Member_GymClasses', {
      
      member_id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        foreignKey: true

      },
      gymClassId: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        foreignKey: true
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });*/
  },
  down: async (queryInterface, Sequelize) => {
    await queryInterface.dropTable('Member_GymClasses');
  }
};