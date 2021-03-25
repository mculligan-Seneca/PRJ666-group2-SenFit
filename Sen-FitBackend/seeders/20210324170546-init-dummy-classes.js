'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
     return  queryInterface.bulkInsert('FitnessClasses',[
      {
        className: 'Yoga Class',
        description: "Calm your senses at Sen-fit's yoga classes.",
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        className: 'Cross-Fit Class',
        description: "Soar to new limits and test your agility in our cross-fit classes",
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        className: 'Peloton Class',
        description: "In this class you get to use our custom pelotons to push to new limits.",
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        className: 'Rock Climbing Class',
        description: "Scale to new heights in our state of the art rock climbing facility!",
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
    return queryInterface.bulkDelete('FitnessClasses',null,{});
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};
