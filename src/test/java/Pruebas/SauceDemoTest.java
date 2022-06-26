package Pruebas;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Paginas.HomePage;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExcel;

public class SauceDemoTest {
	String url = "https://www.saucedemo.com/";
	String driverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	WebDriver driver;
	String dirEvidencias = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "Evidencias-SauceDemo.docx";
		
	@BeforeSuite
	public void abrirPagina() { 
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();	
		driver.get(url);

	}
	@Test (dataProvider="Datos Excel")
	public void generarOrden(String username, String password, String nombre, String apellido, String codigoPostal) throws InvalidFormatException, IOException, InterruptedException {
		
		HomePage inicio = new HomePage(driver);
		
		inicio.Usuario(username);
		inicio.Contraseña(password);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 1 - Login");
		
		inicio.Login();
		
		String urlEsperada = "https://www.saucedemo.com/inventory.html";
		String urlActual = driver.getCurrentUrl();
		Assert.assertEquals(urlActual, urlEsperada);
				
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 2 - Seleccionamos el producto");
		
		inicio.AgregoAlCarrito();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 3 - Ingresamos al carrito");
		
		inicio.Carrito();
		inicio.CheckOut();
		inicio.Nombre(nombre);
		inicio.Apellido(apellido);
		inicio.CodigoPostal(codigoPostal);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 4 - Cargamos los datos de envio");
				
		inicio.Resumen();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 5 - Verificamos la pantalla de resumen de compra");
		
		inicio.Compra();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 6 - Completamos la Compra");
		
		inicio.Inicio();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 7 - Regresamos al menu principal");
		
		inicio.Menu();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 8 - Ingresamos al Menu desplegable y deslogeamos la cuenta");
		
		inicio.Logout();
		
	}
	
	@AfterSuite
	public void cerrarPagina() {
		//driver.close();
	}

	@DataProvider(name="Datos Excel")
	public Object[][] obtenerDatosExcel() throws Exception {
		return DatosExcel.leerExcel("..\\SauceDemo\\Datos\\DatosExel.xlsx", "Hoja1");
	}
}
