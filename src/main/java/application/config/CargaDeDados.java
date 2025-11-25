package application.config; // Ou o pacote onde você colocar

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import application.auth.UsuarioRepository;
import application.auth.Usuario;

@Configuration
public class CargaDeDados implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe o admin para não duplicar
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            // Criptografa a senha "root" antes de salvar
            admin.setPassword(passwordEncoder.encode("root")); 
            
            usuarioRepository.save(admin);
            
            System.out.println(">>> USUÁRIO 'admin' CRIADO COM SUCESSO! SENHA: root <<<");
        }
    }
}