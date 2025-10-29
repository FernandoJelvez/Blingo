package com.blingo.lingdyo;
import java.sql.*;

public class ConexionMySQL {
    private final Connection conn;

    public ConexionMySQL() {
        this.conn = conectarSQL();
    }
    //Modifica USER y PASSWORD si lo necesitas
    //para poder conectarte a la db ↓
    public Connection conectarSQL() {
        Connection connection = null;
        try {
            //ADVERTENCIA: Me falta un comentario completo para agregar la database de blingo
            String sqlURL = "jdbc:mysql://localhost:3306/blingo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String sqlUSER = "blingo";
            String sqlPASSWORD = "#ABcde00";
            connection = DriverManager.getConnection(sqlURL, sqlUSER, sqlPASSWORD);
            System.out.println("Conectado a MySQL");}
        catch (SQLException e) {
            System.err.println("Error conectando a MySQL-Lingdyo:\n" + e.getMessage());}
        return connection;
    }

    public boolean crearTabla(String SQL,String nombre, Boolean error){
        try (Statement state = conn.createStatement()) {
            state.execute(SQL);}
        catch (SQLException e) {
            System.err.println("Error al crear la tabla "+nombre+": \n" + e.getMessage());
            error = true;}
        return error;
    }
    public void tablasBase(){
        boolean error = false;
        /*Las id foraneas de User en tablas no relacionales son para
        diferenciar su creador.*/

        String users = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(15) NOT NULL,
                lastname VARCHAR(15) NOT NULL,
                age INT NOT NULL,
                description VARCHAR(100),
                email VARCHAR(50),
                native_tonge VARCHAR(50)
            )
        """; error = crearTabla(users,"users", error);

        String languages = """
            CREATE TABLE IF NOT EXISTS languages (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(15)
            )
        """; error = crearTabla(languages,"languages",error);

        String courses = """
            CREATE TABLE IF NOT EXISTS courses (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                language_id INT NOT NULL,
                name VARCHAR(50) NOT NULL,
                likes INT DEFAULT 0,
                level VARCHAR(50),
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (language_id) REFERENCES languages(id)
            )
        """; error = crearTabla(courses,"courses", error);

        String contributions = """
            CREATE TABLE IF NOT EXISTS contributions (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                course_id INT NOT NULL,
                type VARCHAR(15) NOT NULL,
                date DATE DEFAULT (CURRENT_DATE()),
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (course_id) REFERENCES courses(id)
            )
        """; error = crearTabla(contributions,"contributions", error);

        String exercises = """
            CREATE TABLE IF NOT EXISTS exercises (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                course_id INT NOT NULL,
                likes INT DEFAULT 0,
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (course_id) REFERENCES courses(id)
            )
        """; error = crearTabla(exercises,"exercises", error);

        String sentences = """
            CREATE TABLE IF NOT EXISTS sentences (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                language_id INT NOT NULL,
                content VARCHAR(100) NOT NULL,
                likes INT DEFAULT 0,
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (language_id) REFERENCES languages(id)
            )
        """; error = crearTabla(sentences,"sentences", error);

        String words = """
            CREATE TABLE IF NOT EXISTS words (
                id INT AUTO_INCREMENT PRIMARY KEY,
                language_id INT NOT NULL,
                content VARCHAR(50) NOT NULL,
                FOREIGN KEY (language_id) REFERENCES languages(id)
            )
        """; error = crearTabla(words,"words", error);

//Tablas relacionales (N-M)
        String users_courses = """
            CREATE TABLE IF NOT EXISTS users_courses (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                course_id INT NOT NULL,
                FOREIGN KEY (user_id) REFERENCES users(id),
                FOREIGN KEY (course_id) REFERENCES courses(id)
            )
        """; error = crearTabla(users_courses, "users_courses", error);

        String exercises_sentences = """
            CREATE TABLE IF NOT EXISTS exercises_sentences (
                id INT AUTO_INCREMENT PRIMARY KEY,
                exercise_id INT NOT NULL,
                sentence_id INT NOT NULL,
                FOREIGN KEY (exercise_id) REFERENCES exercises(id),
                FOREIGN KEY (sentence_id) REFERENCES sentences(id)
            )
        """; error = crearTabla(exercises_sentences,"exercoses_sentences", error);

        String sentences_words = """
            CREATE TABLE IF NOT EXISTS sentences_words (
                id INT AUTO_INCREMENT PRIMARY KEY,
                sentence_id INT NOT NULL,
                word_id INT NOT NULL,
                FOREIGN KEY (sentence_id) REFERENCES sentences(id),
                FOREIGN KEY (word_id) REFERENCES words(id)
            )
        """; error = crearTabla(sentences_words,"sentences_words", error);

        String translates_as = """
            CREATE TABLE IF NOT EXISTS translates_as (
                id INT AUTO_INCREMENT PRIMARY KEY,
                word1_id INT NOT NULL,
                word2_id INT NOT NULL,
                FOREIGN KEY (word1_id) REFERENCES words(id),
                FOREIGN KEY (word2_id) REFERENCES words(id)
            )
        """; error = crearTabla(translates_as,"translates_as", error);

//Notificar por consola en caso de no haber errores.
        if(!error){
            System.out.println("""
                    -Tablas creadas existosamente.""");}
    }

    public void addUser(String name, String pssw){
        String insert = "INSERT INTO users (name, password) VALUES (?,?)";
        try (PreparedStatement values = conn.prepareStatement(insert)) {
            values.setString(1,name);
            values.setString(2,pssw);
            values.execute();
            System.out.println("Usuario agregado correctamente.");}
        catch (SQLException e) {
            System.err.println("Error agregando usuario:\n" + e.getMessage());}
    }
    public void addCourse(int user_id, String name, String description){
        String insert = "INSERT INTO courses (user_id, name, description) VALUES (?,?,?)";
        try (PreparedStatement values = conn.prepareStatement(insert)) {
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
        try (PreparedStatement values = conn.prepareStatement(insert)) {
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
        try (PreparedStatement values = conn.prepareStatement(insert)) {
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
        try (PreparedStatement values = conn.prepareStatement(insert)) {
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
        try (PreparedStatement values = conn.prepareStatement(insert)) {
            values.setInt(1,word_id);
            values.setString(2,description);
            values.execute();
            System.out.println("Definición agregada correctamente.");
        }
        catch (SQLException e) {
            System.err.println("Error agregando definición:\n" + e.getMessage());}
    }
}