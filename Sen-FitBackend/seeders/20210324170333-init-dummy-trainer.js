'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return  queryInterface.bulkInsert('Trainer',[{
      first_name: 'Doug',
      last_name:  'Trains',
      post_code: 'B4U3G3',
      email: 'DougTrains@gmail.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },{
      first_name: 'Mike',
      last_name:  'Johns',
      post_code: 'W4D3Y7',
      email: 'Mike_johns@yahoo.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'Avery',
      last_name:  'Kerkamp',
      post_code: 'L4G3A7',
      email: 'Avery_rocks@bell.net',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'Lindsey',
      last_name:  'Camp',
      post_code: 'R3D7S5',
      email: 'Lcamp@icloud.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'Eren',
      last_name:  'Yeager',
      post_code: 'A8A9L0',
      email: 'freedom@gmail.com',
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
    return queryInterface.bulkDelete('Trainer',null,{});
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};
