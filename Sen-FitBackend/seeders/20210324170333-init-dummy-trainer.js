'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    let i=0;
    const locations = await queryInterface.sequelize.query(`select id from public."GymLocations"`);
    return  queryInterface.bulkInsert('Trainer',[{
      first_name: 'Doug',
      last_name:  'Trains',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'DougTrains@gmail.com',

      createdAt: new Date(),
      updatedAt: new Date()
    },{
      first_name: 'Mike',
      last_name:  'Johns',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'Mike_johns@yahoo.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'Avery',
      last_name:  'Kerkamp',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'Avery_rocks@bell.net',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'Lindsey',
      last_name:  'Camp',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'Lcamp@icloud.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'Eren',
      last_name:  'Yeager',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'freedom@gmail.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'Andie',
      last_name:  'Felterbusch',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'abusch@ymail.com',
      createdAt: new Date(),
      updatedAt: new Date()
    }, {
      first_name: 'James',
      last_name:  'Rhoades',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'RocknRhoades@hotmail.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },{
      first_name: 'Micheal',
      last_name:  'Bolton',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'micheal_bolton@gmail.com',
      createdAt: new Date(),
      updatedAt: new Date()
    },
    {
      first_name: 'John',
      last_name:  'Samuelson',
      gymLocationId: locations[0][i++%locations[0].length].id,
      email: 'jSamuelson@sympatico.ca',
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
