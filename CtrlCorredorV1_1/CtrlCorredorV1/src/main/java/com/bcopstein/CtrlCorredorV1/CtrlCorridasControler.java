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

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasControler {
    private JdbcTemplate jdbcTemplate;
    private RepCorridasControler x = new RepCorridasControler();

    @Autowired
    public CtrlCorridasControler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.jdbcTemplate.execute("DROP TABLE corredores IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE corredores("
                + "cpf VARCHAR(255), nome VARCHAR(255), genero VARCHAR(255), diaDn int, mesDn int, anoDn int, PRIMARY KEY(cpf))");

        this.jdbcTemplate.batchUpdate(
                "INSERT INTO corredores(cpf,nome,genero,diaDn,mesDn,anoDn) VALUES ('10001287','Luiz','masculino',22,5,1987)");

        this.jdbcTemplate.execute("DROP TABLE eventos IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE eventos("
                + "id int, nome VARCHAR(255), dia int, mes int, ano int, distancia int, horas int, minutos int, segundos int,PRIMARY KEY(id))");

/*        this.jdbcTemplate.batchUpdate(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
                " ('1','Poa Day Run',22,5,2019,5,0,35,32)");*/
    }

    @GetMapping("/corredor")
    @CrossOrigin(origins = "*")
    public void consultaCorredor() {
    x.consultaCorredor();
    }

    @PostMapping("/corredor")
    @CrossOrigin(origins = "*")
    public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
    x.cadastraCorredor(corredor);
    return true;
    }

    @GetMapping("/eventos")
    @CrossOrigin(origins = "*")
    public void consultaEventos() {
        x.consultaEventos();
    }

    @PostMapping("/eventos") // adiciona evento no único corredor
    @CrossOrigin(origins = "*")
    public boolean informaEvento(@RequestBody final Evento evento) {
     x.informaEvento(evento);
     return true;
    }
}
