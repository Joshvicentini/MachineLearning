package br.com.ufc.ia.machinelearning.algorithm.network;


public class Processamento {

    public int Processa(long[] valor) {

        RedeNeural rede_entrada = null;
        Neuronio neuronio_entrada = new Neuronio();
        int retorno = 0;

        neuronio_entrada.setValor(valor);

        try {
            rede_entrada = RedeNeuralUtil.txtToJava(".\\arquivo\\camada_entrada.txt");
        } catch (Exception e) {
            System.out.println("Arquivo nao pode ser lido!");
        }
        if (rede_entrada != null) {

            // BEGIN CAMADA DE ENTRADA

            Neuronio vencedor_entrada = new Neuronio();
            vencedor_entrada = rede_entrada.Vencedor(neuronio_entrada);

            // END CAMADA DE ENTRADA

            try {
            	RedeNeuralUtil.javaToTxt(rede_entrada, ".\\arquivo\\camada_entrada.txt");
            } catch (Exception e) {
                System.out.println("Arquivo nao pode ser escrito!");
            }

            RedeNeural rede_saida = null;

            try {
                rede_saida = RedeNeuralUtil.txtToJava(".\\arquivo\\camada_saida.txt");
            } catch (Exception e) {
                System.out.println("Arquivo nao pode ser lido!");
            }
            if (rede_saida != null) {

                // TREINAR REDE

                //rede_saida.Treinar(rede_entrada.getConjunto(), 1000);

                // FIM TREINAR REDE



                // BEGIN CAMADA DE SAIDA

                Neuronio vencedor_saida = new Neuronio();
                vencedor_saida = rede_saida.Vencedor(vencedor_entrada);

                // FIM CAMADA DE SAIDA

                retorno = (int) vencedor_saida.Norma();

                try {
                	RedeNeuralUtil.javaToTxt(rede_saida, ".\\arquivo\\camada_saida.txt");
                } catch (Exception e) {
                    System.out.println("Arquivo nao pode ser escrito!");
                }

            }
        }

        return retorno;
    }

    public int Mapeamento(int entrada) {

        if (entrada == 1283) {
            return 10;
        }

        if (entrada == 2017) {
            return 10;
        }

        if (entrada == 2830) {
            return 51;
        }

        if (entrada == 1202) {
            return 35;
        }

        if (entrada == 5783) {
            return 14;
        }

        if (entrada == 2207) {
            return 10;
        }

        if (entrada == 1352) {
            return 14;
        }

        if (entrada == 2472) {
            return 35;
        }

        if (entrada == 10062) {
            return 6;
        }

        if (entrada == 25216) {
            return 2;
        }

        if (entrada == 5326) {
            return 6;
        }

        if (entrada == 1283) {
            return 10;
        }
        if (entrada == 1202) {
            return 85;
        }

        if (entrada == 2830) {
            return 34;
        }

        if (entrada == 1202) {
            return 22;
        }

        if (entrada == 25216) {
            return 26;
        }

        if (entrada == 6980) {
            return 22;
        }

        if (entrada == 7679) {
            return 91;
        }

        if (entrada == 1911) {
            return 10;
        }

        if (entrada == 2125) {
            return 273;
        }

        if (entrada == 2032) {
            return 19;
        }

        if (entrada == 2280) {
            return 6;
        }

        if (entrada == 2032) {
            return 6;
        }

        if (entrada == 3231) {
            return 114;
        }

        if (entrada == 2267) {
            return 2;
        }

        return 0;
    }
}

