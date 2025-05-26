# API Endpoints - Sistema de Evaluación de Riesgo Crediticio

## Configuración Base
- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/json`

## 📋 Endpoints Disponibles

### 1. Evaluar Riesgo Crediticio
**POST** `/api/evaluar-riesgo`

Evalúa el riesgo crediticio de un cliente (persona natural o jurídica).

#### Request Body - Persona Natural
```json
{
  "tipoCliente": "NATURAL",
  "nombre": "Juan Pérez",
  "puntajeCrediticio": 720,
  "montoSolicitado": 15000.00,
  "plazoEnMeses": 24,
  "edad": 35,
  "ingresoMensual": 2500.00,
  "deudasActuales": [
    {
      "monto": 3000.00,
      "plazoMeses": 12
    },
    {
      "monto": 1500.00,
      "plazoMeses": 6
    }
  ]
}
```

#### Request Body - Persona Jurídica
```json
{
  "tipoCliente": "JURIDICA",
  "nombre": "Empresa ABC S.A.",
  "puntajeCrediticio": 680,
  "montoSolicitado": 50000.00,
  "plazoEnMeses": 36,
  "antiguedadAnios": 5,
  "ingresoAnual": 120000.00,
  "empleados": 25,
  "deudasActuales": [
    {
      "monto": 8000.00,
      "plazoMeses": 18
    }
  ]
}
```

#### Response - Aprobado
```json
{
  "nivelRiesgo": "BAJO",
  "aprobado": true,
  "puntajeFinal": 85,
  "mensaje": "Riesgo Bajo: Crédito aprobado sin restricciones.",
  "tasaInteres": 6.5,
  "plazoAprobado": 24
}
```

#### Response - Rechazado
```json
{
  "nivelRiesgo": "ALTO",
  "aprobado": false,
  "puntajeFinal": 45,
  "mensaje": "Cliente no apto para préstamo",
  "tasaInteres": 0,
  "plazoAprobado": 0
}
```

---

### 2. Obtener Historial de Evaluaciones
**GET** `/api/historial/{clienteId}`

Obtiene el historial de evaluaciones de un cliente específico.

#### URL Parameters
- `clienteId` (Long): ID del cliente

#### Ejemplo de Request
```
GET http://localhost:8080/api/historial/1
```

#### Response
```json
[
  {
    "id": 1,
    "clienteNombre": "Juan Pérez",
    "tipoCliente": "NATURAL",
    "montoSolicitado": 15000.00,
    "plazoEnMeses": 24,
    "nivelRiesgo": "BAJO",
    "aprobado": true,
    "fechaConsulta": "2024-01-15T10:30:00"
  },
  {
    "id": 2,
    "clienteNombre": "Juan Pérez",
    "tipoCliente": "NATURAL",
    "montoSolicitado": 25000.00,
    "plazoEnMeses": 36,
    "nivelRiesgo": "MEDIO",
    "aprobado": true,
    "fechaConsulta": "2024-01-20T14:15:00"
  }
]
```

#### Response - Sin historial
```json
[]
```

---

## 🧪 Casos de Prueba para Postman

### Test Case 1: Persona Natural - Riesgo Bajo
```json
{
  "tipoCliente": "NATURAL",
  "nombre": "María González",
  "puntajeCrediticio": 750,
  "montoSolicitado": 10000.00,
  "plazoEnMeses": 12,
  "edad": 28,
  "ingresoMensual": 3000.00,
  "deudasActuales": [
    {
      "monto": 500.00,
      "plazoMeses": 6
    }
  ]
}
```

### Test Case 2: Persona Natural - Riesgo Alto
```json
{
  "tipoCliente": "NATURAL",
  "nombre": "Carlos Rodríguez",
  "puntajeCrediticio": 580,
  "montoSolicitado": 20000.00,
  "plazoEnMeses": 48,
  "edad": 45,
  "ingresoMensual": 1800.00,
  "deudasActuales": [
    {
      "monto": 5000.00,
      "plazoMeses": 24
    },
    {
      "monto": 3000.00,
      "plazoMeses": 12
    }
  ]
}
```

### Test Case 3: Persona Jurídica - Riesgo Medio
```json
{
  "tipoCliente": "JURIDICA",
  "nombre": "TechSoft Solutions Ltda.",
  "puntajeCrediticio": 670,
  "montoSolicitado": 75000.00,
  "plazoEnMeses": 60,
  "antiguedadAnios": 3,
  "ingresoAnual": 200000.00,
  "empleados": 15,
  "deudasActuales": [
    {
      "monto": 15000.00,
      "plazoMeses": 30
    }
  ]
}
```

### Test Case 4: Persona Jurídica - Sin Deudas
```json
{
  "tipoCliente": "JURIDICA",
  "nombre": "StartUp Innovadora S.A.S.",
  "puntajeCrediticio": 720,
  "montoSolicitado": 30000.00,
  "plazoEnMeses": 24,
  "antiguedadAnios": 2,
  "ingresoAnual": 80000.00,
  "empleados": 8,
  "deudasActuales": []
}
```

### Test Case 5: Persona Natural - Múltiples Deudas
```json
{
  "tipoCliente": "NATURAL",
  "nombre": "Ana Martínez",
  "puntajeCrediticio": 650,
  "montoSolicitado": 12000.00,
  "plazoEnMeses": 18,
  "edad": 32,
  "ingresoMensual": 2200.00,
  "deudasActuales": [
    {
      "monto": 2000.00,
      "plazoMeses": 8
    },
    {
      "monto": 1000.00,
      "plazoMeses": 4
    },
    {
      "monto": 800.00,
      "plazoMeses": 6
    }
  ]
}
```

---

## 📊 Parámetros de Evaluación

### Persona Natural
- **Puntaje Crediticio**: 300-850
- **Edad**: Mínimo 18 años
- **Ingreso Mensual**: Mayor a 0
- **Penalizaciones**:
  - Puntaje < 650: -30 puntos
  - Deudas > 40% ingreso: -15 puntos
  - Monto solicitado > 50% ingreso: -10 puntos

### Persona Jurídica
- **Antigüedad**: Mínimo 0 años
- **Empleados**: Mínimo 1
- **Ingreso Anual**: Mayor a 0
- **Penalizaciones**:
  - Puntaje < 650: -30 puntos
  - Deudas > 35% ingreso: -20 puntos
  - Monto solicitado > 30% ingreso: -15 puntos

### Niveles de Riesgo
- **BAJO**: Puntaje ≥ 80 (Tasa: 6.5%)
- **MEDIO**: Puntaje ≥ 60 (Tasa: 8.0%)
- **ALTO**: Puntaje < 60 (Rechazado)
