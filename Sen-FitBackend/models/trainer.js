'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Trainer extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({GymClass,GymLocation,TrainingPlan,OnlineClass}) {
      // define association here
      this.hasMany(GymClass,{foreignKey:'trainerId',as: 'gymClasses'});
      this.hasMany(OnlineClass,{foreignKey:'trainerId',as:'onlineClasses'});
      this.hasMany(TrainingPlan,{foreignKey:'trainerId',as: 'trainingPlans'});
      this.belongsTo(GymLocation,{foreignKey:'gymLocationId'});

    }
    
  };
  Trainer.init({
    first_name: DataTypes.STRING,
    last_name: DataTypes.STRING,
    email: DataTypes.STRING,
    gymLocationId: {
        type: DataTypes.INTEGER,
        allowNull:true
    }
  }, {
    sequelize,
    modelName: 'Trainer',
    freezeTableName: true
  });
  return Trainer;
};