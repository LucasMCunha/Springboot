package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class RepCorridasControler {
    private JdbcTemplate jdbcTemplate;

    public List<Corredor> consultaCorredor() {
        List<Corredor> resp = this.jdbcTemplate.query("SELECT * from corredores",
                (rs, rowNum) -> new Corredor(rs.getString("cpf"), rs.getString("nome"), rs.getInt("diaDn"),
                        rs.getInt("mesDn"), rs.getInt("anoDn"), rs.getString("genero")));
        return resp;
    }

    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
        // Limpa a base de dados
        this.jdbcTemplate.batchUpdate("DELETE from Corredores");
        // Então cadastra o novo "corredor único"
        this.jdbcTemplate.update("INSERT INTO corredores(cpf,nome,diaDn,mesDn,anoDn,genero) VALUES (?,?,?,?,?,?)",
                corredor.getCpf(), corredor.getNome(), corredor.getDiaDn(), corredor.getMesDn(), corredor.getAnoDn(),
                corredor.getGenero());
        return true;
    }

    public List<Evento> consultaEventos() {
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos",
                (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
        return resp;
    }

    public boolean informaEvento(@RequestBody final Evento evento) {
        this.jdbcTemplate.update(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES (?,?,?,?,?,?,?,?,?)",
                evento.getId(), evento.getNome(), evento.getDia(), evento.getMes(), evento.getAno(),
                evento.getDistancia(), evento.getHoras(), evento.getMinutos(), evento.getSegundos());
        return true;
    }
}
