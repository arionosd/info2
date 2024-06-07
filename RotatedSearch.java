/**
 * Klasse zur Bereitstellung einer Methode <code>find</code>,
 * die fuer ein aufsteigend sortiertes Feld von paarweise verschiedenen
 * Werten, das um eine unbekannte Anzahl von Malen zyklisch verschoben wurde, 
 * angibt, an welcher Stelle ein gegebenes Element vorhanden ist.
 */
public class RotatedSearch {

	/**
	 * Bestimme, ob und ggfs. an welcher Stelle das gesuchte Element
	 * im angegebenen Feld enthalten ist. Aus Effizienzgruenden wird
	 * nicht geprueft, ob dieses Feld wirklich ein aufsteigend sortiertes,
	 * zyklisch rotiertes Feld ist.
	 * @param <T>     Typ der im Feld gespeicherten Elemente
	 * @param array   Aufsteigend sortiertes, zyklisch rotiertes Feld
	 *                mit paarweise verschiedenen Elementen
	 * @param element Zu suchendes Element
	 * @return Index, an dem das Element gespeichert ist, -1, falls
	 *         das Element nicht gespeichert ist.
	 */
	public static <T extends Comparable<T> > int find(T[] array, T element) {
		int index = -1; // Element nicht gefunden
	
		if (array ==null) {
			throw new IllegalArgumentException ("Das uebergebene Feld darf nicht null sein.");
		}
		
		int anfang = 0;
		int ende = array.length -1;
		
		// Finden des Elements, das als erstes in das Array reinrotiert wurde (z.B. die "8" aus dem
		// Aufgabenblatt)
		while (anfang < ende) {
			int mitte = (int) Math.floor(anfang + ((ende-anfang))/2);
			
			if (array[mitte].compareTo(array[ende]) > 0) {
				anfang = mitte+1;
			} else {
				ende = mitte;
			}
		}
		
		// Der Index des Drehelements ist der neue Anfang (anfang und ende werden an den Indizes 
		// der Begrenzungen des Feldes gesetzt)
		int drehElement = anfang;
		anfang = 0;
		ende = array.length-1;
		
		// Wenn das gesuchte element ≥ das Drehelement und gleichzeitig ≤ das Element am Ende des Feldes
		// weiss man, dass auf dieser rechten "Seite" unser Element sich potenziell befinden kann.
		// Die Binaere Suche wird auf den Bereich vom Drehelement bis zum Ende des Feldes begrenzt bzw.
		// vom Anfang des Feldes bis zum Drehelement, wenn element ≤ Drehelement && ≥ Element am Ende des Feldes.
		if (element.compareTo(array[drehElement])>=0 && element.compareTo(array[ende])<= 0) {
			anfang = drehElement;
		} else {
			ende = drehElement;
		}
		
		// Binaere Suche ab hier:
		while (anfang <= ende) {
			int mitte = (int) Math.floor(anfang + ((ende - anfang) / 2));
			if (array[mitte].compareTo(element) == 0) {
				// Element wurde gefunden
				index = mitte;
				return index;
			} else if (element.compareTo(array[mitte]) < 0) {
				// linke Haelfte
				ende = mitte - 1;
			} else if (element.compareTo(array[mitte]) > 0) {
				// rechte Haelfte
			    anfang = mitte + 1;
			}			
		}
		
		return index;
	}
}
