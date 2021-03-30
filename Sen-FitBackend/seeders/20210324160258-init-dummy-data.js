'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    
    
    
    
    const trainers = await queryInterface.sequelize.query(`select id,"gymLocationId" from public."Trainer"`);
   
    const classes = await queryInterface.sequelize.query(`select id from public."FitnessClasses"`);
    
   return queryInterface.bulkInsert('GymClass',[
      {
        class_date: new Date("2021-04-14 00:00:00"),
        start_time: new Date("2021-04-14 11:00:00"),
        end_time: new Date("2021-04-14 11:30:00"),
        trainerId: trainers[0][4-1].id,
        gymLocationId: trainers[0][4-1].gymLocationId,
        fitnessClassId: classes[0][1-1].id,
        enrolled: false,
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
          class_date: new Date("2021-04-14 00:00:00"),
          start_time: new Date("2021-04-14 12:00:00"),
          end_time: new Date("2021-04-14 12:30:00"),
          trainerId: trainers[0][4-1].id,
          gymLocationId: trainers[0][4-1].gymLocationId,
          fitnessClassId: classes[0][1-1].id,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-14 00:00:00"),
          start_time: new Date("2021-04-14 13:00:00"),
          end_time: new Date("2021-04-14 13:30:00"),
          trainerId: trainers[0][4-1].id,
          gymLocationId: trainers[0][4-1].gymLocationId,
          fitnessClassId: classes[0][1-1].id,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-15 00:00:00"),
          start_time: new Date("2021-04-15 9:00:00"),
          end_time: new Date("2021-04-15 9:45:00"),
          trainerId: trainers[0][5-1].id,
          gymLocationId: trainers[0][5-1].gymLocationId,
          fitnessClassId: classes[0][4-1].id,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-15 00:00:00"),
          start_time: new Date("2021-04-15 10:00:00"),
          end_time: new Date("2021-04-15 10:45:00"),
          trainerId: trainers[0][5-1].id,
          gymLocationId: trainers[0][5-1].gymLocationId,
          fitnessClassId: classes[0][4-1].id,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-15 00:00:00"),
          start_time: new Date("2021-04-15 11:15:00"),
          end_time: new Date("2021-04-15 12:00:00"),
          trainerId: trainers[0][5-1].id,
          gymLocationId: trainers[0][5-1].gymLocationId,
          fitnessClassId: classes[0][4-1].id,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-16 00:00:00"),
          start_time: new Date("2021-04-16 9:15:00"),
          end_time: new Date("2021-04-16 9:45:00"),
          trainerId: trainers[0][5-1].id,
          fitnessClassId: classes[0][3-1].id,
          gymLocationId: trainers[0][5-1].gymLocationId,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-16 00:00:00"),
          start_time: new Date("2021-04-16 11:00:00"),
          end_time: new Date("2021-04-16 11:30:00"),
          trainerId: trainers[0][5-1].id,
          fitnessClassId: classes[0][3-1].id,
          gymLocationId: trainers[0][5-1].gymLocationId,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-13 00:00:00"),
          start_time: new Date("2021-04-13 11:00:00"),
          end_time: new Date("2021-04-13 11:30:00"),
          trainerId: trainers[0][3-1].id,
          fitnessClassId: classes[0][2-1].id,
          gymLocationId: trainers[0][3-1].gymLocationId,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-13 00:00:00"),
          start_time: new Date("2021-04-13 14:10:00"),
          end_time: new Date("2021-04-13 14:40:00"),
          trainerId: trainers[0][3-1].id,
          fitnessClassId: classes[0][1-1].id,
          gymLocationId: trainers[0][3-1].gymLocationId,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-17 00:00:00"),
          start_time: new Date("2021-04-17 17:00:00"),
          end_time: new Date("2021-04-17 17:45:00"),
          trainerId: trainers[0][3-1].id,
          fitnessClassId: classes[0][4-1].id,
          gymLocationId: trainers[0][3-1].gymLocationId,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },{
          class_date: new Date("2021-04-20 00:00:00"),
          start_time: new Date("2021-04-20 9:00:00"),
          end_time: new Date("2021-04-20 9:45:00"),
          trainerId: trainers[0][1-1].id,
          fitnessClassId: classes[0][4-1].id,
          gymLocationId: trainers[0][1-1].gymLocationId,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-20 00:00:00"),
          start_time: new Date("2021-04-20 10:15:00"),
          end_time: new Date("2021-04-20 11:00:00"),
          trainerId: trainers[0][1-1].id,
          fitnessClassId: classes[0][4-1].id,
          gymLocationId: trainers[0][1-1].gymLocationId,
          enrolled: false,
          createdAt: new Date(),
          updatedAt: new Date()
        },
        {
          class_date: new Date("2021-04-11 00:00:00"),
          start_time: new Date("2021-04-11 9:15:00"),
          end_time: new Date("2021-04-11 9:30:00"),
          trainerId: trainers[0][1-1].id,
          fitnessClassId: classes[0][1-1].id,
          gymLocationId: trainers[0][1-1].gymLocationId,
          enrolled: false,
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
    
  
    return queryInterface.bulkDelete('GymClass',null,{});
   

    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};
