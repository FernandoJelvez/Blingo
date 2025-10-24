package com.blingo.lingdyo;
import java.sql.*;

public class ConexionMySQL {
    //NOTA: No conectara si no creas la base de datos local, ve a tu MySQL y usa "CREATE database if not exists lingdyo"
    private String URL = "jdbc:mysql://localhost:3306/lingdyo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String USER = "root";
    private String PASSWORD = "1q2w3e4r";
    private Connection CONN;
    /*
    Modifica USER y PASSWORD si lo necesitas para poder conectarte a tu base de datos local
     */
    public ConexionMySQL() {
        this.CONN = conectarSQL();
    }
    public Connection conectarSQL() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado a MySQL");}
        catch (SQLException e) {
            System.err.println("Error conectando a MySQL-Lingdyo:\n" + e.getMessage());}
        return conn;
    }

    public boolean crearTabla(String SQL,String nombre){
        boolean error = false;
        try (Statement state = CONN.createStatement()) {
            state.execute(SQL);}
        catch (SQLException e) {
            System.err.println("Error al crear la tabla "+nombre+": \n" + e.getMessage());
            error = true;}
        return error;
    }
    public void tablasBase(){
        boolean error = false;

        String users = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                password VARCHAR(12)
            )
        """; error = crearTabla(users,"users");

        String courses = """
            CREATE TABLE IF NOT EXISTS courses (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                name VARCHAR(50) NOT NULL,
                description VARCHAR(100),
                likes INT DEFAULT 0,
                FOREIGN KEY (user_id) REFERENCES users(id)
            )
        """; error = crearTabla(courses,"courses");

        String sentences = """
            CREATE TABLE IF NOT EXISTS sentences (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                course_id INT NOT NULL,
                name VARCHAR(50) NOT NULL,
                description VARCHAR(100),
                likes INT DEFAULT 0,
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (course_id) REFERENCES courses(id)
            )
        """; error = crearTabla(sentences,"sentences");

        String exercises = """
            CREATE TABLE IF NOT EXISTS exercises (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                sentence_id INT NOT NULL,
                name VARCHAR(50) NOT NULL,
                description VARCHAR(100),
                likes INT DEFAULT 0,
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (sentence_id) REFERENCES sentences(id)
            )
        """; error = crearTabla(exercises,"exercises");

        String words = """
            CREATE TABLE IF NOT EXISTS words (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                name VARCHAR(50) NOT NULL,
                FOREIGN KEY (user_id) REFERENCES users(id)
            )
        """; error = crearTabla(words,"words");

        String meanings = """
            CREATE TABLE IF NOT EXISTS meanings (
                id INT AUTO_INCREMENT PRIMARY KEY,
                word_id INT NOT NULL,
                description VARCHAR(100) NOT NULL,
                FOREIGN KEY (word_id) REFERENCES words(id)
            )
        """; error = crearTabla(meanings,"meanings");
//Tablas relacionales
        String sentences_words = """
            CREATE TABLE IF NOT EXISTS sentences_words (
                id INT AUTO_INCREMENT PRIMARY KEY,
                sentence_id INT NOT NULL,
                word_id INT NOT NULL,
                FOREIGN KEY (sentence_id) REFERENCES sentences(id),
                FOREIGN KEY (word_id) REFERENCES words(id)
            )
        """; error = crearTabla(sentences_words,"sentences_words");

        if(!error){
            System.out.println("""
                    -Tablas: users, courses, sentences
                    exercises, words, meanings y sentences_words.
                    -Creadas existosamente.""");}
    }

    public void addUser(String name, String pssw){
        String insert = "INSERT INTO users (name, password) VALUES (?,?)";
        try (PreparedStatement values = CONN.prepareStatement(insert)) {
            values.setString(1,name);
            values.setString(2,pssw);
            values.execute();
            System.out.println("Usuario agregado correctamente.");}
        catch (SQLException e) {
            System.err.println("Error agregando usuario:\n" + e.getMessage());}
    }
    public void addCourse(int user_id, String name, String description){
        String insert = "INSERT INTO courses (user_id, name, description) VALUES (?,?,?)";
        try (PreparedStatement values = CONN.prepareStatement(insert)) {
            values.setInt(1,user_id);
            values.setString(2,name);
            values.setString(3,description);
            values.execute();
            System.out.println("Curso agregado correctamente.");}
        catch (SQLException e) {
            System.err.println("Error agregando curso:\n" + e.getMessage());}
    }
    public void addSentence(int user_id, int course_id, String name, String description){
        String insert = "INSERT INTO sentences (user_id, course_id, name, description) VALUES (?,?,?,?)";
        try (PreparedStatement values = CONN.prepareStatement(insert)) {
            values.setInt(1,user_id);
            values.setInt(2,course_id);
            values.setString(3,name);
            values.setString(4,description);
            values.execute();
            System.out.println("Frase agregado correctamente.");}
        catch (SQLException e) {
            System.err.println("Error agregando frase:\n" + e.getMessage());}
    }
    public void addEsercise(int user_id, int sentence_id, String name, String description){
        String insert = "INSERT INTO exercises (user_id, sentence_id, name, description) VALUES (?,?,?,?)";
        try (PreparedStatement values = CONN.prepareStatement(insert)) {
            values.setInt(1,user_id);
            values.setInt(2,sentence_id);
            values.setString(3,name);
            values.setString(4,description);
            values.execute();
            System.out.println("Ejercicio agregado correctamente.");
        }
        catch (SQLException e) {
            System.err.println("Error agregando ejercicio:\n" + e.getMessage());}
    }
    public void addWord(int user_id, String name){
        String insert = "INSERT INTO words (user_id, name) VALUES (?,?)";
        try (PreparedStatement values = CONN.prepareStatement(insert)) {
            values.setInt(1,user_id);
            values.setString(2,name);
            values.execute();
            System.out.println("Palabra agregada correctamente.");
        }
        catch (SQLException e) {
            System.err.println("Error agregando palabra:\n" + e.getMessage());}
    }
    public void addMeaning(int word_id, String description){
        String insert = "INSERT INTO meanings (word_id, description) VALUES (?,?)";
        try (PreparedStatement values = CONN.prepareStatement(insert)) {
            values.setInt(1,word_id);
            values.setString(2,description);
            values.execute();
            System.out.println("Definición agregada correctamente.");
        }
        catch (SQLException e) {
            System.err.println("Error agregando definición:\n" + e.getMessage());}
    }
}