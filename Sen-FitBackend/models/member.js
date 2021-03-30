'use strict';
const {
  Model
} = require('sequelize');
const trainer = require('./trainer');
const {uuid}= require('uuidv4');
module.exports = (sequelize, DataTypes) => {
  class Member extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({FitnessPortfolio, GymClass,CovidLog,TrainingPlan}) {
      // define association here
      this.hasMany(CovidLog,{foreignKey: 'member_id'});
      this.hasMany(FitnessPortfolio,{foreignKey: 'member_id'});
      this.hasMany(TrainingPlan,{foreignKey:'member_id'});
      this.belongsToMany(GymClass, {through: 'Member_GymClass'});//many to many 
      
    }

    //maybe override toJson 
  };
  Member.init({
    
    firstName: DataTypes.STRING,
    lastName: DataTypes.STRING,
    postalCode: DataTypes.STRING,
    birth_date: DataTypes.DATE,
    gender: DataTypes.STRING,
    email: {
      type: DataTypes.STRING,
      unique:true,
    },
    password: DataTypes.STRING,
    salt: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'Member',
  });
  //Member.beforeCreate(user=>user.uuid=uuid());
  return Member;
};