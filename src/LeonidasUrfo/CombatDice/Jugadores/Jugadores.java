package LeonidasUrfo.CombatDice.Jugadores;

import org.bukkit.entity.Player;

public class Jugadores extends Player.Spigot{

	private static int UUID;
	private int sUUID;
	private String nombre;
	private boolean enCombate = false;
	private boolean miTurno = false;
	private int MaxTurno;
	private int Destreza;
	private int Agilidad;
	private int Fuerza;
	private int Intelecto;
	private int Constitucion;
	private int Magia;
	private int Resistencia;
	private int Sabiduria;
	
	public static int getUUID() {
		return UUID;
	}

	public static void setUUID(int uUID) {
		UUID = uUID;
	}

	public int getsUUID() {
		return sUUID;
	}

	public void setsUUID(int sUUID) {
		this.sUUID = sUUID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isenCombate() {
		return enCombate;
	}

	public void setenCombate(boolean estado) {
		this.enCombate = estado;
	}
	
	public boolean isMiTurno() {
		return miTurno;
	}

	public void setMiTurno(boolean miTurno) {
		this.miTurno = miTurno;
	}

	public int getMaxTurno() {
		return MaxTurno;
	}

	public void setMaxTurno(int maxTurno) {
		MaxTurno = maxTurno;
	}

	public int getDestreza() {
		return Destreza;
	}

	public void setDestreza(int destreza) {
		Destreza = destreza;
	}

	public int getAgilidad() {
		return Agilidad;
	}

	public void setAgilidad(int agilidad) {
		Agilidad = agilidad;
	}

	public int getFuerza() {
		return Fuerza;
	}

	public void setFuerza(int fuerza) {
		Fuerza = fuerza;
	}

	public int getIntelecto() {
		return Intelecto;
	}

	public void setIntelecto(int intelecto) {
		Intelecto = intelecto;
	}

	public int getConstitucion() {
		return Constitucion;
	}

	public void setConstitucion(int constitucion) {
		Constitucion = constitucion;
	}

	public int getMagia() {
		return Magia;
	}

	public void setMagia(int magia) {
		Magia = magia;
	}

	public int getResistencia() {
		return Resistencia;
	}

	public void setResistencia(int resistencia) {
		Resistencia = resistencia;
	}

	public int getSabiduria() {
		return Sabiduria;
	}

	public void setSabiduria(int sabiduria) {
		Sabiduria = sabiduria;
	}
	
	public void getAllInfo(Player player) {
		player.sendMessage("nombre: "+getNombre()+" UUID: "+getsUUID()+" está en combate: "+isenCombate()+" es mi turno: "+isMiTurno()+" Máximo de turnos: "+getMaxTurno());
	}
	
	public Jugadores(String nombre, boolean enCombate) {
		UUID = UUID + 1;
		sUUID = UUID;
		this.nombre = nombre;
		this.enCombate = enCombate;
		Destreza = 0;
		Agilidad = 0;
		Fuerza = 0;
		Intelecto = 0;
		Constitucion = 0;
		Magia = 0;
		Resistencia = 0;
		Sabiduria = 0;
	}
	
	public void loader(int Destreza, int Agilidad, int Fuerza, int Intelecto, int Constitucion, int Magia, int Resistencia, int Sabiduria) {
		this.Destreza = Destreza;
		this.Agilidad = Agilidad;
		this.Fuerza = Fuerza;
		this.Intelecto = Intelecto;
		this.Constitucion = Constitucion;
		this.Magia = Magia;
		this.Resistencia = Resistencia;
		this.Sabiduria = Sabiduria;
	}
}
