package com.idega.block.building.business;



/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      idega multimedia
 * @author       <a href="mailto:aron@idega.is">aron@idega.is</a>
 * @version 1.0
 */

public class BuildingFinder {

  public final  static int APARTMENT = 2,FLOOR=3,BUILDING=4,COMPLEX=5,CATEGORY=6,TYPE=7;

/*
  public static List listOfComplex(){
    try{
      return EntityFinder.getInstance().findAll(Complex.class);
    }
    catch(Exception e){return null;}
  }
  public static List listOfBuilding(){
    try{
      return EntityFinder.getInstance().findAll(Building.class);
    }
    catch(Exception e){return null;}
  }

  public static List listOfFloor(){
    try{
      return EntityFinder.getInstance().findAll(Floor.class);
    }
    catch(Exception e){return null;}
  }

  public static List listOfApartmentType(){
    try{
     return EntityFinder.getInstance().findAll(ApartmentType.class);
    }
    catch(Exception e){return null;}
  }

  public static List listOfApartmentCategory(){
    try{
     return EntityFinder.getInstance().findAll(ApartmentCategory.class);
    }
    catch(Exception e){return null;}
  }

  public static List listOfApartment(){
    try{
     return EntityFinder.getInstance().findAll(Apartment.class);
    }
    catch(Exception e){return null;}
  }

  public static List ListOfAparmentOrderedByFloor(){
    try{
     return EntityFinder.getInstance().findAllOrdered(Apartment.class,com.idega.block.building.data.ApartmentBMPBean.getFloorIdColumnName());
    }
    catch(Exception e){return null;}
  }


  public static Floor[] findFloors(){
    Floor[] floors = new Floor[0];
    try{
    	List l =  EntityFinder.getInstance().findAll(Floor.class);
    	return (Floor[]) l.toArray();
    }
    catch(Exception e){}
    return floors;
  }

  public static RoomType[] findRoomTypes(){
    RoomType[] types = new RoomType[0];
    try{
    types = (RoomType[]) (((com.idega.block.building.data.RoomTypeHome)com.idega.data.IDOLookup.getHomeLegacy(RoomType.class)).createLegacy()).findAll();
    }
    catch(SQLException e){}
    return types;
  }

  public static ApartmentType[] findApartmentTypesInBuilding(int iBuildingId){
    ApartmentType[] rt = new ApartmentType[0];
   
    StringBuffer sql = new StringBuffer(" select distinct bu_aprt_type.* ");
    sql.append(" from bu_aprt_type p,bu_apartment a,bu_floor f ");
    sql.append(" where p.bu_aprt_type_id = a.bu_aprt_type_id");
    sql.append(" and a.bu_floor_id = f.bu_floor_id");
    sql.append(" and f.bu_building_id = ");
    sql.append(iBuildingId);
    
    StringBuffer sql = new StringBuffer("select distinct ");
     sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append(".* from ");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append(" p ,");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getNameTableName());
    sql.append(" a ,");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append(" f where p.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id = a.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id and a.");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getFloorIdColumnName());
    sql.append(" = f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append("_id and f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getBuildingIdColumnName());
    sql.append(" = ");
    sql.append(iBuildingId);

    try{
    	List l = EntityFinder.getInstance().findAll(ApartmentType.class,sql.toString());
      rt= (ApartmentType[])l.toArray();
    }
    catch(Exception ex){}
    return rt;
  }

  public static String[] findDistinctApartmentTypesInComplex(int iComplexId) {
    StringBuffer sql = new StringBuffer("select distinct ");
     sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getApartmentCategoryIdColumnName());
    sql.append(" from ");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append(" p ,");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getNameTableName());
    sql.append(" a ,");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append(" f ,");
    sql.append(com.idega.block.building.data.BuildingBMPBean.getNameTableName());
    sql.append(" b where p.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id = a.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id and a.");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getFloorIdColumnName());
    sql.append(" = f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append("_id and f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getBuildingIdColumnName());
    sql.append(" = b.");
    sql.append(com.idega.block.building.data.BuildingBMPBean.getNameTableName());
    sql.append("_id and b.");
    sql.append(com.idega.block.building.data.ComplexBMPBean.getNameTableName());
    sql.append("_id = ");
    sql.append(iComplexId);
    sql.append(" order by ");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getApartmentCategoryIdColumnName());

    String[] L = null;
    try{
     L = com.idega.data.SimpleQuerier.executeStringQuery(sql.toString());
    }
    catch(Exception e){}
    return L;
  }

  public static ApartmentType[] findApartmentTypesInCategory(int iCategoryId){
    ApartmentType[] rt = new ApartmentType[0];
    try {
    	List l = EntityFinder.getInstance().findAllByColumn(ApartmentType.class,ApartmentTypeBMPBean.getApartmentCategoryIdColumnName(),iCategoryId);
      rt = (ApartmentType[]) l.toArray();
    }
    catch (Exception ex) {

    }
    return rt;
  }

  public static ApartmentType[] findApartmentTypesInComplex(int iComplexId){
    ApartmentType[] rt = new ApartmentType[0];
   
    StringBuffer sql = new StringBuffer("select distinct ");
     sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append(".* from ");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append(" p ,");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getNameTableName());
    sql.append(" a ,");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append(" f ,");
    sql.append(com.idega.block.building.data.BuildingBMPBean.getNameTableName());
    sql.append(" b where p.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id = a.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id and a.");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getFloorIdColumnName());
    sql.append(" = f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append("_id and f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getBuildingIdColumnName());
    sql.append(" = b.");
    sql.append(com.idega.block.building.data.BuildingBMPBean.getNameTableName());
    sql.append("_id and b.");
    sql.append(com.idega.block.building.data.ComplexBMPBean.getNameTableName());
    sql.append("_id = ");
    sql.append(iComplexId);

    try{
    	List l = EntityFinder.getInstance().findAll(ApartmentType.class,sql.toString());
      rt= (ApartmentType[])l.toArray();
    }
    catch(Exception ex){}
    return rt;
  }

  public static ApartmentType[] findApartmentTypesForCategory(int categoryId) {
    ApartmentType aprtType[] = null;
    try {
    	List l = EntityFinder.getInstance().findAllByColumn(ApartmentType.class,ApartmentTypeBMPBean.getApartmentCategoryIdColumnName(),categoryId);
      aprtType = (ApartmentType[]) l.toArray();
    }
    catch(Exception e) {
    }

    return(aprtType);
  }

  public static int getComplexIdFromTypeId(int id) {
    StringBuffer sql = new StringBuffer("select distinct ");
    sql.append(com.idega.block.building.data.ComplexBMPBean.getNameTableName());
    sql.append(".* from ");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append(" p ,");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getNameTableName());
    sql.append(" a ,");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append(" f ,");
    sql.append(com.idega.block.building.data.ComplexBMPBean.getNameTableName());
    sql.append(" c ,");
    sql.append(com.idega.block.building.data.BuildingBMPBean.getNameTableName());
    sql.append(" b where p.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id = a.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id and a.");
    sql.append(com.idega.block.building.data.ApartmentBMPBean.getFloorIdColumnName());
    sql.append(" = f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getNameTableName());
    sql.append("_id and f.");
    sql.append(com.idega.block.building.data.FloorBMPBean.getBuildingIdColumnName());
    sql.append(" = b.");
    sql.append(com.idega.block.building.data.BuildingBMPBean.getNameTableName());
    sql.append("_id and b.");
    sql.append(com.idega.block.building.data.ComplexBMPBean.getNameTableName());
    sql.append("_id = c.");
    sql.append(com.idega.block.building.data.ComplexBMPBean.getNameTableName());
    sql.append("_id and a.");
    sql.append(com.idega.block.building.data.ApartmentTypeBMPBean.getNameTableName());
    sql.append("_id = ");
    sql.append(id);
    int r = -1;
    Complex[] c = new Complex[0];
    try{
    	List l = EntityFinder.getInstance().findAll(Complex.class,sql.toString());
      c= (Complex[])l.toArray();
      if(c.length > 0)
        r = c[0].getID();
    }
    catch(Exception ex){}
    return r;

  }

  public static Hashtable getLodgingsHash(){
    Hashtable hashtable = new Hashtable();
    List BuildingList = listOfBuilding();
    List FloorList = listOfFloor();
    List TypeList = listOfApartmentType();
    List CategoryList = listOfApartmentCategory();
    List ComplexList  = listOfComplex();
    if(ComplexList != null){
      int clen = ComplexList.size();
      Complex C;
      for (int i = 0; i < clen; i++) {
        C = (Complex) ComplexList.get(i);
        hashtable.put("x_"+C.getID(),C);
      }
    }
    if(BuildingList != null){
      int clen = BuildingList.size();
      Building B;
      for (int i = 0; i < clen; i++) {
        B = (Building) BuildingList.get(i);
        hashtable.put("b_"+B.getID(),B);
      }
    }
    if(FloorList != null){
      int len = FloorList.size();
      Floor F;
      for (int i = 0; i < len; i++) {
        F = (Floor) FloorList.get(i);
        hashtable.put("f_"+F.getID(),F);
      }
    }
    if(TypeList != null){
      int len = TypeList.size();
      ApartmentType T;
      for (int i = 0; i < len; i++) {
        T = (ApartmentType) TypeList.get(i);
        hashtable.put("t_"+T.getID(),T);
      }
    }
    if(CategoryList != null){
      int len = CategoryList.size();
      ApartmentCategory C;
      for (int i = 0; i < len; i++) {
        C = (ApartmentCategory) CategoryList.get(i);
        hashtable.put("c_"+C.getID(),C);
      }
    }

    return hashtable;
  }

  public static List listOfApartmentsInType(int id){
   
    List L = null;
    try{
     L = EntityFinder.getInstance().findAllByColumnOrdered(Apartment.class,ApartmentBMPBean.getNameTableName()+"_id",String.valueOf(id),com.idega.block.building.data.ApartmentBMPBean.getNameColumnName());
    }
    catch(Exception sql){}
    return L;
  }

  public static List listOfApartmentsInTypeAndComplex(int typeId,int cmplxId){
    
    List L = null;
    StringBuffer sql = new StringBuffer("select bu_apartment.* ");
    sql.append("from bu_apartment,bu_floor,bu_building,bu_aprt_type,bu_complex ");
    sql.append("where bu_apartment.bu_aprt_type_id = bu_aprt_type.bu_aprt_type_id ");
    sql.append("and bu_apartment.bu_floor_id = bu_floor.bu_floor_id ");
    sql.append("and bu_building.bu_building_id = bu_floor.bu_building_id ");
    sql.append("and bu_building.bu_complex_id = bu_complex.bu_complex_id ");
    sql.append("and bu_complex.bu_complex_id = ");
    sql.append(cmplxId);
    sql.append(" and bu_aprt_type.bu_aprt_type_id = ");
    sql.append(typeId);
    sql.append(" order by bu_apartment.bu_floor_id ");

    try{
      L = EntityFinder.getInstance().findAll(Apartment.class,sql.toString());
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    return L;
  }
  
  public static List searchApartment(String searchname){
    
    List L = null;
    try {
      L = EntityFinder.getInstance().findAllByColumnOrdered(Apartment.class,ApartmentBMPBean.getNameColumnName(),searchname,ApartmentBMPBean.getFloorIdColumnName());
    }
    catch (Exception ex) {

    }
    return L;
  }
  public static Vector getApartmentTypesComplexForCategory(int categoryId) {
    Vector v = new Vector();
    Connection Conn = null;

    String sqlString = "select * from v_cam_aprt_type_complex where bu_aprt_cat_id = " + categoryId;

    try {
      Conn = com.idega.util.database.ConnectionBroker.getConnection();
      Statement stmt = Conn.createStatement();
      ResultSet RS  = stmt.executeQuery(sqlString);

      while (RS.next()) {
        ApartmentTypeComplexHelper appHelp = new ApartmentTypeComplexHelper();
        int typeId = RS.getInt("bu_aprt_type_id");
        int complexId = RS.getInt("bu_complex_id");
        String typeName = RS.getString("aprt_type_name");
        String complexName = RS.getString("complex_name");

        appHelp.setKey(typeId,complexId);
        appHelp.setName(typeName + " (" + complexName + ")");
        v.addElement(appHelp);
      }

      RS.close();
      stmt.close();
    }
    catch(SQLException e) {
      System.err.println(e.toString());
    }
    finally{
      com.idega.util.database.ConnectionBroker.freeConnection(Conn);
    }

    return(v);
  }

  public static Vector getAllApartmentTypesComplex() {
    Vector v = new Vector();
    Connection Conn = null;

    String sqlString = "select * from v_cam_aprt_type_complex";

    try {
      Conn = com.idega.util.database.ConnectionBroker.getConnection();
      Statement stmt = Conn.createStatement();
      ResultSet RS  = stmt.executeQuery(sqlString);

      while (RS.next()) {
        ApartmentTypeComplexHelper appHelp = new ApartmentTypeComplexHelper();
        int typeId = RS.getInt("bu_aprt_type_id");
        int complexId = RS.getInt("bu_complex_id");
        String typeName = RS.getString("aprt_type_name");
        String complexName = RS.getString("complex_name");

        appHelp.setKey(typeId,complexId);
        appHelp.setName(typeName + " (" + complexName + ")");
        v.addElement(appHelp);
      }

      RS.close();
      stmt.close();
    }
    catch(SQLException e) {
      System.err.println(e.toString());
    }
    finally{
      com.idega.util.database.ConnectionBroker.freeConnection(Conn);
    }

    return(v);
  }

  public static List listOfBuildingsInComplex(int id){
     
    List L = null;
    try {
      L = EntityFinder.getInstance().findAllByColumn(Building.class,BuildingBMPBean.getComplexIdColumnName(),id);
    }
    catch (Exception ex) {

    }
    return L;

  }

  public static List listOfBuildingImageFiles(int iComplexId){
    try {
      String sql = "select distinct f.* from ic_file f, bu_building  b where  b.ic_image_id = f.ic_file_id and b.bu_complex_id = "+iComplexId;
     //System.err.println(sql);
      return EntityFinder.findAll(com.idega.core.file.data.ICFileBMPBean.getStaticInstance(com.idega.core.file.data.ICFile.class),sql);
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;

  }

  public static int countApartmentsInTypeAndComplex(int typeId,int cmplxId){
    StringBuffer sql = new StringBuffer("select count(*) ");
    sql.append(" from bu_apartment a,bu_floor f,bu_building b,bu_complex c,bu_aprt_type t ");
    sql.append(" where a.bu_aprt_type_id = t.bu_aprt_type_id ");
    sql.append(" and a.bu_floor_id = f.bu_floor_id ");
    sql.append(" and f.bu_building_id = b.bu_building_id ");
    sql.append(" and b.bu_complex_id = c.bu_complex_id ");
    sql.append(" and t.bu_aprt_type_id = ");
    sql.append(typeId);
    sql.append(" and c.bu_complex_id = ");
    sql.append(cmplxId);
    int count = 0;
    try{
    	
      count = ((ComplexHome)IDOLookup.getHome(Complex.class)).create().getNumberOfRecords(sql.toString());
    }
    catch(Exception ex){}
    if(count < 0)
      count = 0;
    return count;
  }
  
  public static int countRentableApartments(){
    String sql = "select count(*) from "+com.idega.block.building.data.ApartmentBMPBean.getNameTableName() +" where "+com.idega.block.building.data.ApartmentBMPBean.getRentableColumnName()+" = 'Y'";
    int count = 0;
    //System.err.println(sql.toString());
    try{
      count = ((ApartmentHome)IDOLookup.getHome(Apartment.class)).create().getNumberOfRecords(sql.toString());

    }
    catch(Exception ex){ex.printStackTrace();}
    if(count < 0)
      count = 0;
    return count;
  }

   public static List listOfApartments(String sComplexId,String sBuildingId,String sFloorId,String sType,String sCategory,int iOrder){

    StringBuffer sql = new StringBuffer("select a.* ");
    sql.append(" from bu_apartment a,bu_floor f,bu_building b");
    sql.append(",bu_complex c,bu_aprt_type t,bu_aprt_cat y");
    sql.append(" where a.bu_aprt_type_id = t.bu_aprt_type_id ");
    sql.append(" and t.bu_aprt_cat_id = y.bu_aprt_cat_id");
    sql.append(" and a.bu_floor_id = f.bu_floor_id ");
    sql.append(" and f.bu_building_id = b.bu_building_id ");
    sql.append(" and b.bu_complex_id = c.bu_complex_id ");

    if(sComplexId !=null && !"-1".equals(sComplexId)){
      sql.append(" and bu_complex_id  = ");
      sql.append(sComplexId);
    }
    if(sBuildingId !=null && !"-1".equals(sBuildingId)){
      sql.append(" and bu_building_id = ");
      sql.append(sBuildingId);
    }
    if(sFloorId !=null && !"-1".equals(sFloorId)){
      sql.append(" and bu_floor_id = ");
      sql.append(sFloorId);
    }
    if(sType !=null && !"-1".equals(sType)){
      sql.append(" and bu_aprt_type_id = ");
      sql.append(sType);
    }
    if(sCategory !=null && !"-1".equals(sCategory)){
      sql.append(" and bu_aprt_cat_id = ");
      sql.append(sCategory);
    }
    String order = getOrderString(iOrder);
    if(order != null){
      sql.append(" order by ");
      sql.append(order);
    }

    try{
      return  EntityFinder.getInstance().findAll(Apartment.class,sql.toString());
    }
    catch(Exception ex){
      return null;
    }
  }

  private static String getOrderString(int type){
    String order = null;
    switch (type) {
      case BUILDING : order = " b.name " ; break;
      case COMPLEX: order =  " c.name " ; break;
      case FLOOR:  order =  " f.name " ; break;
      case APARTMENT: order =" a.name " ; break;
      case CATEGORY:  order =  " y.name " ; break;
      case TYPE: order =" t.name " ; break;
      default: order = " a.bu_apartment_id ";

    }
    return order;
  }
*/
}// class end
