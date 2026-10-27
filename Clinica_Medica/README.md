# Sistema de Gestão de Clínica Médica (Java, console)

Requisitos: JDK 17 ou superior (nenhuma biblioteca externa).

## Compilar e executar
Duplo clique em `compilar.bat` e depois `executar.bat`
IDE (IntelliJ/Eclipse/VS Code): importe a pasta `src` e execute `clinica.app.Main`

Na primeira execução são carregados dados de exemplo e criado o arquivo `clinica.dat`.
Para recomeçar do zero, apague o `clinica.dat`.

## Estrutura
- `src/clinica/modelo`  -> classes do domínio (diagrama de classes) e enums
- `src/clinica/servico` -> regras de negócio (ClinicaService), "banco" em arquivo e exceção
- `src/clinica/app`     -> menus (Main), leitura do teclado (Console) e dados de exemplo

