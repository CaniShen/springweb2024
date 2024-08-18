package utilidades;

import org.springframework.stereotype.Component;

import entities.Cliente;
import entities.Cuenta;
import entities.Movimiento;
import model.ClienteDto;
import model.CuentaDto;
import model.MovimientoDto;

@Component
public class Mapeador {
	public ClienteDto clienteEntityToDto(Cliente cliente) {
		return new ClienteDto(cliente.getDni(),
								cliente.getNombre(),
								cliente.getDireccion(),
								cliente.getTelefono());
	}
	
	public MovimientoDto movimientoEntityToDto(Movimiento movimiento) {
		return new MovimientoDto(movimiento.getIdMovimiento(),
									movimiento.getCuenta().getNumeroCuenta(),
									movimiento.getFecha(),
									movimiento.getCantidad(),
									movimiento.getOperacion());
	}
	
	
	public CuentaDto cuentaEntityToDto(Cuenta cuenta) {
		return new CuentaDto(cuenta.getNumeroCuenta(),
							cuenta.getSaldo(),
							cuenta.getTipoCuenta());
							
	}

}
