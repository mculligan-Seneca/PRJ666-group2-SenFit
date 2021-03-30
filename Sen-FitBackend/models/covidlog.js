'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class CovidLog extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate({Member}) {
      this.belongsTo(Member,{foreignKey:'member_id'});
      // define association here
    }
  };
  CovidLog.init({
    status: DataTypes.BOOLEAN,
    date_logged: DataTypes.DATE,
    member_id: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'CovidLog',
  });
  return CovidLog;
};