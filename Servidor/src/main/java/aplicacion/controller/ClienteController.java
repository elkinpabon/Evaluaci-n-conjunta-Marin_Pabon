package aplicacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import aplicacion.dto.ClienteRequestDTO;
import aplicacion.dto.HistorialEvaluacionDTO;
import aplicacion.dto.ResultadoEvaluacionDTO;
import aplicacion.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/evaluar-riesgo")
    public ResultadoEvaluacionDTO evaluarRiesgo(@RequestBody ClienteRequestDTO dto) {
        return clienteService.evaluarRiesgo(dto);
    }

    @GetMapping("/historial/{idCliente}")
    public List<HistorialEvaluacionDTO> obtenerHistorial(@PathVariable Long idCliente) {
        return clienteService.obtenerHistorial(idCliente);
    }
}
