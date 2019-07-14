package pl.pf.sonalake.exeption;

/**
 * Wyjątek związany z komunikacją z NBP
 *
 * @author pfutro
 */
public class NbpExeption extends RuntimeException {

        public NbpExeption(String message) {
            super(message);
        }

}
