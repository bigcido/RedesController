package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedeController {
	
	public RedeController() {
		super();
	}
	
	private String os() {
		return System.getProperty("os.name").toLowerCase();
	}
	
	public void ip() {
		String os = os();
		try {
			Process process;
			if (os.contains("windows")) {
				process = Runtime.getRuntime().exec("ipconfig");
				System.out.println("Executando no Windows");
			} else if (os.contains("linux") || (os.contains("nix")) || os.contains("mac")){
				process = Runtime.getRuntime().exec("ifconfig");
				System.out.println("Executando no Linux / MAC");
			} else {
				System.out.println("Sistema não encontrado");
				return;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linha;
            String nomeAdaptador = "";

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim(); // Remover espaços em branco no início e no fim da linha

                if (linha.contains("Adaptador")) {
                    nomeAdaptador = linha; // Armazenar o nome do adaptador
                } else if (linha.contains("IPv4") && linha.matches(".*\\d+\\.\\d+\\.\\d+\\.\\d+.*")) {
                    String[] partes = linha.split(": ");
                    System.out.println("Adaptador: " + nomeAdaptador);
                    System.out.println("Endereço IPv4: " + partes[1]);
                    System.out.println("------------------");
                }
            }
            reader.close();
        
			}	catch(Exception e) {
			e.printStackTrace();
			}
		}
	public void ping() {
        String os = os();
        
        try {
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("ping -n 10 www.google.com.br");
            } else if (os.contains("linux") || os.contains("nix") || os.contains("mac")) {
                process = Runtime.getRuntime().exec("ping -c 10 www.google.com.br");
            } else {
                System.out.println("Sistema Operacional não suportado: " + os);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); 
                if (os.contains("win")) {
                    if (line.contains("Média") || line.contains("Average")) {
                        System.out.println("Tempo médio: " + line.trim());
                    }
                }
                
                else if (os.contains("linux") || os.contains("nix") || os.contains("mac")) {
                    if (line.contains("rtt min/avg/max/mdev") || line.contains("round-trip")) {
                        String[] partes = line.split("/");
                        String mediaPing = partes[4].trim();
                        System.out.println("Tempo médio: " + mediaPing + " ms");
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}