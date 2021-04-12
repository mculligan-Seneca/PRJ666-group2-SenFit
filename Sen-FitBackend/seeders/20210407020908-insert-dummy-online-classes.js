'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    let fitnessClasses = await queryInterface.sequelize.query('select id from public."FitnessClasses"');
    let trainers = await queryInterface.sequelize.query('select id from public."Trainer"');
    fitnessClasses=fitnessClasses[0];
    trainers=trainers[0];
    
    
    return queryInterface.bulkInsert('OnlineClasses',[
      {
        classDate: new Date('2021-04-22'),
        start_time: new Date('2021-04-22 09:30:00'),
        end_time: new Date('2021-04-22 10:00:00') ,
        FitnessClassId: fitnessClasses[2%fitnessClasses.length].id ,
        trainerId: trainers[0%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()

      },{
        classDate: new Date('2021-04-22'),
        start_time: new Date('2021-04-22 10:05:00'),
        end_time: new Date('2021-04-22 10:35:00') ,
        FitnessClassId: fitnessClasses[2%fitnessClasses.length].id ,
        trainerId: trainers[0%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()

      },{
        classDate: new Date('2021-04-20'),
        start_time: new Date('2021-04-20 09:00:00'),
        end_time: new Date('2021-04-20 09:45:00') ,
        FitnessClassId: fitnessClasses[4%fitnessClasses.length].id ,
        trainerId: trainers[1%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-05-01'),
        start_time: new Date('2021-05-01 08:00:00'),
        end_time: new Date('2021-05-01 09:00:00') ,
        FitnessClassId: fitnessClasses[7%fitnessClasses.length].id ,
        trainerId: trainers[6%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-05-01'),
        start_time: new Date('2021-05-01 09:15:00'),
        end_time: new Date('2021-05-01 10:15:00') ,
        FitnessClassId: fitnessClasses[7%fitnessClasses.length].id ,
        trainerId: trainers[6%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-05-01'),
        start_time: new Date('2021-05-01 13:00:00'),
        end_time: new Date('2021-05-01 14:00:00') ,
        FitnessClassId: fitnessClasses[7%fitnessClasses.length].id ,
        trainerId: trainers[6%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-04-29'),
        start_time: new Date('2021-04-29 13:00:00'),
        end_time: new Date('2021-04-29 14:00:00') ,
        FitnessClassId: fitnessClasses[7%fitnessClasses.length].id ,
        trainerId: trainers[5%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-04-29'),
        start_time: new Date('2021-04-29 13:00:00'),
        end_time: new Date('2021-04-29 14:00:00') ,
        FitnessClassId: fitnessClasses[6%fitnessClasses.length].id ,
        trainerId: trainers[3%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      }, {
        classDate: new Date('2021-04-28'),
        start_time: new Date('2021-04-28 11:00:00'),
        end_time: new Date('2021-04-28 11:45:00') ,
        FitnessClassId: fitnessClasses[3%fitnessClasses.length].id ,
        trainerId: trainers[2%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-04-28'),
        start_time: new Date('2021-04-28 11:50:00'),
        end_time: new Date('2021-04-28 12:30:00') ,
        FitnessClassId: fitnessClasses[3%fitnessClasses.length].id ,
        trainerId: trainers[2%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-05-04'),
        start_time: new Date('2021-05-04 08:30:00'),
        end_time: new Date('2021-05-04 09:00:00') ,
        FitnessClassId: fitnessClasses[0%fitnessClasses.length].id ,
        trainerId: trainers[0%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },
      {
        classDate: new Date('2021-05-04'),
        start_time: new Date('2021-05-04 09:45:00'),
        end_time: new Date('2021-05-04 10:15:00') ,
        FitnessClassId: fitnessClasses[2%fitnessClasses.length].id ,
        trainerId: trainers[2%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
      },  {
        classDate: new Date('2021-05-04'),
        start_time: new Date('2021-05-04 12:45:00'),
        end_time: new Date('2021-05-04 13:30:00') ,
        FitnessClassId: fitnessClasses[2%fitnessClasses.length].id ,
        trainerId: trainers[2%trainers.length].id,
        enrolled: false,
        updatedAt: new Date(),
        createdAt: new Date()
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
    return queryInterface.bulkDelete('OnlineClasses');
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};
