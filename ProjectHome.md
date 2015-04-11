En los últimos años mi trabajo ha estado centrado en el desarrollo de backends normalmente relacionados con todo tipo de integraciones de sistemas de control, inmotica, control de accesos, control de presencia, etc.

Estos desarrollos suelen disponer como máximo de un servidor telnet que ofrece un shell mediante el cual se pueden lanzar comandos interactivos (igual que en un linux).

En los últimos desarrollos he tenido que incluir algunos comandos para configurar los sistemas que necesitan sentencias de mas de 200 caracteres por lo que interactuar con una de estas aplicaciones se esta convirtiendo en un infierno y por ese motivo ha llegado el momento de dotar de interface Web a estas aplicaciones.

En su momento hice una incursion a los interfaces de usuario con GWT y desarrolle http://www.santjoans.es (codigo fuente disponible aquí https://code.google.com/p/santjoans-gwt/ ) pero realmente no tengo mucha experiencia por lo que estuve estudiando bastantes posibilidades:
  * [GWT](https://developers.google.com/web-toolkit/)
  * [VAADIN](https://vaadin.com/home)
  * [RAP](http://eclipse.org/rap/)
  * [SENCHA](http://www.sencha.com/)
  * [QOOXDOO](http://qooxdoo.org/)
  * [DOJO](http://dojotoolkit.org/)

El sistema que he elegido es VAADIN (en su recién estrenada versión 7) y algunos de los motivos han sido:
  * Puedo continuar desarrollando en Java sin tener que fragmentar todavía mas mi cerebro.
  * Puedo utilizar la experiencia que acumule en el pasado en GWT para desarrollar partes del cliente VAADIN ([mas..](https://vaadin.com/book/vaadin7/-/page/part-clientside.html))
  * Toda la lógica reside en el servidor lo cual permite que los sistemas sean mas seguros.
  * Puede utilizarse tanto para aplicaciones tradicionales (con deploy WAR) como en aplicaciones OSGI puras.
  * Es escalable [Mas..](http://www.slideshare.net/codento/vaadin-scalabilityslides)
  * 9 años de historia y una comunidad creciendo mucho [Mas..](http://en.wikipedia.org/wiki/Vaadin)
  * Muy productivo: Un solo programador ha sido capaz de implementar un renderizador de Eclipse E4 antes que el equipo de Eclipse RAP haya sido capaz de conseguirlo (ver mas abajo).

Por supuesto también existen algunos puntos negativos:
  * Al residir la logica en el servidor, los interfaces desarrollados con VAADIN funcionan mejor en una intranet que en internet (aun así funciona bastante bien, ver [sampler](http://demo.vaadin.com/sampler) y [dashboard demo](http://demo.vaadin.com/dashboard))
  * Algunos componentes están sujetos a licencia comercial (como [este](http://demo.vaadin.com/charts)). De algo tienen que vivir...

Una prueba de las enormes posibilidades de VAADIN es [este proyecto (ver demos online)](https://vaadin.com/directory#addon/vaaclipse) en el cual VAADIN se utiliza como renderizador de la nueva tecnologia de eclipse RCP 4 (antes conocido como E4) proporcinando un runtime OSGI y potente inyeccion de dependencias que simplifica el desarrollo de los UIs una barbaridad.

Esto es lo que me gustaría utilizar a mi, pero desgraciadamente este proyecto no soporta VAADIN 7 y ademas no tiene una comunidad detrás suficientemente solida (y no tengo suficiente nivel de E4 para meterme en el lio de colaborar en el).

## Por donde empiezo ##

Decidi empezar a utilizar VAADIN de la forma mas natural y esto era con una aplicación WAR que se desplegase en un contenedor de Servlets.

Al estar acostumbrado a utilizar OSGI y por tanto disponer de una gran infraestructura para mis desarrollos en seguida tuve la sensación de que había que complementar VAADIN con algunas cosas.

Empece a hacer distintas pruebas que coloque en este repositorio que finalmente he decidido hacer publicas pues es de justicia teniendo en cuenta las cantidad de información que he utilizado y que otras personas habían compartido.

Todavía no me he puesto en serio con VAADIN pero si que se necesitan unas nociones mínimas para poder seguir las pruebas.

La principales fuente de información de VAADIN son:
  * [VAADIN BOOK](http://www.vaadin.com/book)
  * [API Javadoc](http://www.vaadin.com/api)

## prueba00 ##

Tanto para poder integrar VAADIN en un futuro con OSGI como para poder integrar VAADIN con
[Google Guice](https://code.google.com/p/google-guice/) (inyección de dependencias) se necesita sustitur el **`UIProvider`** por defecto que lee la clase del UI de entrada desde el **`web.xml`**.

## prueba01 ##

Ejemplo de uso básico de como utilzar [Google Guice](https://code.google.com/p/google-guice/) para ensamblar la aplicación.

VAADIN parece que esta implementando [su propia inyección de dependencias](https://vaadin.com/wiki/-/wiki/Main/Vaadin-CDI-integration) y parece ser que estaría disponible relativamente pronto pero después de ver el código fuente realmente parece que esta en pañales y por ese motivo he decidido continuar con la implementacion de google.

Al pulsar el botón el texto que se mostrara en pantalla es el URI Fragment (es decir, al URI de la aplicación añadir `#UnTexto` en el momento de lanzarla.

Información suplementaria:
  * https://code.google.com/p/google-guice/wiki/Motivation?tm=6
  * http://codeturm.blogspot.com.es/2013/02/eclipse-gae-guice-and-vaadin-700.html
  * http://rndjava.blogspot.com.es/2012/12/vaadin-guice-and-shiro.html

## prueba02 ##

Después de hacer la primera pasada de la documentación de VAADIN parece claro que las aplicaciones tiene que estructurarse de forma que se soporten los fragmentos del URI de la aplicación para poder acceder a cualquier pantalla de esta directamente (soporte de navegación) y soportar también el Bookmark del navegador.

Se utiliza como base el ejemplo de libro https://vaadin.com/book/vaadin7/-/page/advanced.navigator.html sin Google Guice.

## prueba03 ##

Se migra la prueba02 para que se ensamble mediante inyección de dependencias ([Google Guice](https://code.google.com/p/google-guice/)).

El VAADIN versión 7 cambia el concepto de Application por UI y permite que en una pagina Web puedan incrustarse varias instancias de un mismo UI y también de distintos UI ([mas..](https://vaadin.com/book/vaadin7/-/page/advanced.embedding.html))

Por ese motivo se implementa para Google Guice un nuevo tipo de Scope que permite disponer de un Singleton para objetos inyectados en el ambito de cada instancia de un UI.

En esta prueba de una forma mas o menos manual se verifica que el nuevo `UIScope` funciona correctamente.

Introduzco el uso de la libreria de Google [Guava](https://code.google.com/p/guava-libraries/) para forzarme a ir aprendiendo a utilizarla (por productividad).

## prueba04 ##

La mayoría de información que he ido viendo de autenticacion/autorizacion para Vaadin sugiere que utilice [Apache Shiro](http://shiro.apache.org/) que ademas esta perfectamente integrada con Google Guice.

Después de hacer algunas pruebas me resisto ya que la API es bastante farragosa y lo intento con JAAS. Había estudiado bastante esta API hace tiempo aunque nunca me había atrevido a utilizarla pero después de la pruebas creo que es la que mas me conviene.

Conceptualmente es mas compleja, pero una vez la entiendes es mucho mas flexible, la API mas corta y mas próxima a la VM.

Ademas, si después quieres activar las extensiones de seguridad de OSGI, estas son JAAS puras por lo que conviene tener experiencia con ella antes.

Creo que la seguridad en OSGI me permitirá establecer que determinados servicios solo puedan ser ejecutados por código firmado por mi lo cual permitiría entre otras cosas un correcto sistema de licencias.

En esta prueba el código de autorización y autenticacion utiliza una abstracción de un DAO para obtener los datos del modelo de seguridad y aunque en la prueba se implementa mediante colecciones esta diseñado para poder adaptarse a cualquier tipo de persistencia.

Todo el código de autenticacion y autorización se implementa programaticamente (por tanto sin ficheros de configuración) y contra un modelo de seguridad vía DAO. Creo que queda verificada la posibilidad de implementar cualquier modelo de seguridad.

Mas información:
  * http://www.jaasbook.com/
  * http://www.edc4it.com/2011/05/12/understanding-java-security-and-jaas-part-2/

## prueba05 ##

Mediante la implementacion de AOP de Google Guice se ha instalado un interceptor del método que recibe el evento del cambio de vista.

El código JAAS que verifica que el usuario tiene permiso para acceder a la nueva vista se a pasado desde este método a la clase interceptora del método.

Ademas a la clase interceptora se le ha podido inyectar el servicio con el cual obtenemos cual es el usuario activo (la inyección de dependencias en el interceptor es posible).

Todos los cambios que se han hecho respecto a la prueba anterior estan concentrados en el paquete `aprendevaadin.prueba05.demo.view`.

## prueba06 ##

Tanto VAADIN como Google Guice hace uso intensivo del patron ThreadLocal y me ha parecido conveniente echarle una mirada a la documentación de Oracle y hacer una prueba muy simple para asentar el concepto.

## prueba07 ##

Pruebas de uso del ComboBox utilizando el Container implícito que este incorpora.

## prueba08 ##

Se implementa desde cero un Contenedor, un Item y un par de Propiedades para finalmente  enganchar el contenedor con un ComboBox.

Esto es un ejercicio para empezar la innmersion en el diseño de Contenedores ya que no parece practico el uso en un ComoboBox.

## prueba09 ##

Ejemplo muy sencillo de push server

## prueba10 ##

Ejemplo complejo de push server con una tabla a la cual le asignamos
un Container que se alimenta de un modelo el cual tiene un thread haciendo todo tipo de cambios en sus datos.

Ver video en http://youtu.be/2XiQ1YfLjSM

## prueba11 ##

Ejemplo muy sencillo de como manipular un Label con CSS

## prueba12 ##

Ejemplo complejo de push server con un arbol (Tree) al cual le asignamos
un Container que se alimenta de un modelo el cual tiene un thread haciendo todo tipo de cambios en sus datos.

## prueba13 ##

Pruebas de implementacion de un contenedor con **lazy loading** mediante la implementacion del interface `Container.Indexed` (las dudas surgen porque este interface hereda `Container.Ordered`).

En definitiva, hay que implementar `Container`, `Container.Ordered` y `Container.Indexed` lo cual son un montón de métodos y algunos muy complejos de escribir si el origen de datos es JDBC.
En esta prueba se implementan todos los métodos sobre datos en memoria y permite observar que en realidad solo se necesita implementar unos pocos metodos.
