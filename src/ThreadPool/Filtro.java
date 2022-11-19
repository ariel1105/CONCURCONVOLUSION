package ThreadPool;

public enum Filtro {


        BLUR("blur", new double[][]{{1/9d,1/9d,1/9d},{1/9d,1/9d,1/9d},{1/9d,1/9d,1/9d}}),
        SHARPEN("sharpen", new double[][]{{0,-1,0},{-1,5,-1},{0,-1,0}}),
        SOBEL_VERTICAL("sobel vertical", new double[][]{{1d,0,-1d},{2d,0,-2d},{1d,0,-1d}}),
        SOBEL_HORIZONTAL("sobel horizontal", new double[][]{{1d,2d,1d},{0,0,0},{-1d,-2d,-1d}}),
        LAPLACIAN("laplacian" , new double[][]{{0d,1d,0d},{1d,-4d,1d},{0d,1d,0d}}),
        EMBOSS("emboss", new double[][]  {{-2d,-1d,0d},{-1d,1d,1d},{0d,1d,2d}});


        private final String nombre;
        private final double[][] matriz;
    private Filtro(final String nombre , final double[][] matriz) {
        this.nombre = nombre;
        this.matriz = matriz;
    }


        public String getNombre() {
            return nombre;
        }

        public double[][] getMatriz() {
            return matriz;
        }
        public static double[][] getByName(final String nombre) {
            for (final Filtro filtro : values()) {
                if (filtro.getNombre().equals(nombre)) {
                    return filtro.getMatriz();
                }
            }
            throw new IllegalArgumentException("No existe el filtro " + nombre);
        }
    }
