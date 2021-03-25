'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
      return queryInterface.bulkInsert('GymLocations',[
      {
        postal_code: 'A8A91',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        postal_code: 'R3D7S6',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        postal_code: 'L4G3A8',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);
    /**
     * Add seed commands here.
     *
     * Example:
     * await queryInterface.bulkInsert('People', [{
     *   name: 'John Doe',
     *   isBetaMember: false
     * }], {});
    */
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('GymLocations',null,{});
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};
