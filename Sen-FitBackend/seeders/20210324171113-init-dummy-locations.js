'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
      return queryInterface.bulkInsert('GymLocations',[
      {
        postal_code: 'A8A91',
        street_address: '123 Main Street',
        province: 'Ontario',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        postal_code: 'R3D7S6',
        street_address: '23 Queens Street',
        province: 'Ontario',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        postal_code: 'L4G3A8',
        street_address: '28th Younge Street',
        province: 'Ontario',
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
