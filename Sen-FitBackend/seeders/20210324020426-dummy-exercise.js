'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
      return queryInterface.bulkInsert('Exercises',[
      {
        exerciseName: "Push up",
        exerciseDescription: "A conditioning exercise performed in a prone position by raising and lowering the body with the straightening and bending of the arms while keeping the back straight " +
        "and supporting the body on the hands and toes.",
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        exerciseName: "Sit up",
        exerciseDescription: "Situps are classic abdominal exercises done " +
        "by lying on your back and lifting your torso. " +
        "They use your body weight to strengthen " +
        "and tone the core-stabilizing abdominal muscles.",
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {exerciseName: "forward lunge",
      exerciseDescription: "Stand with feet hip-width apart," +
      " engage your core, " +
      "and take a big step backward. Activate your glutes as " +
      "you bend front knee to lower your body so back knee lightly " +
      "taps the floor while keeping upper body upright. " +
      "Drive front heel into the floor to return to starting position. Repeat on the other side",
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
      return queryInterface.bulkDelete('Exercises',null,{});
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};
