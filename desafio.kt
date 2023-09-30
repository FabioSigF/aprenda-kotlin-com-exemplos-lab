import kotlin.random.Random

enum class Nivel { BASICO, INTERMEDIARIO, AVANÇADO }

class Usuario(val nome: String, var sobrenome: String, var idade: Int){
    var matricula: String? = null;
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel);

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel) {
    val inscritos: MutableList<Usuario> = mutableListOf();

    fun matricular(vararg usuarios: Usuario) {
        for(usuario in usuarios) {
            usuario.matricula = "${usuario.nome.first()}${usuario.sobrenome.first()}${inscritos.size+1}"
            inscritos.add(usuario);
        }

    }

    fun mostrarInscritos() {
        println("==========================\nINSCRITOS\n==========================")
        for (inscrito in inscritos) {
            println("Nome: ${inscrito.nome}\nSobrenome: ${inscrito
                .sobrenome}\nIdade: ${inscrito.idade}\nMatricula: " +
                    "${inscrito.matricula}\n");
        }
    }

    fun mostrarConteudos() {
        println("==========================\nFORMAÇÃO $nome\n==========================\nCatálogo:")
        for(conteudo in conteudos) {
            println("   ${conteudo.nome}   ${conteudo.nivel}   ${conteudo.duracao}min")
        }
    }
}

fun main() {
    //USUÁRIOS
    val usuarioFabio: Usuario = Usuario("Fábio", "Signorini", 22);
    val usuarioFelipe: Usuario = Usuario("Felipe", "Signorini", 22);

    //LISTA DE CONTEÚDO EDUCACIONAL
    val conteudoEducacional: MutableList<ConteudoEducacional> = mutableListOf();
    conteudoEducacional.apply {
        this.add(ConteudoEducacional("Estrutura de Controle de Fluxo e Coleções em Kotlin",120, Nivel.BASICO))
        this.add(ConteudoEducacional("Introdução ao Kotlin", 120, Nivel.BASICO))
        this.add(ConteudoEducacional("Orientação a Objetos e Tipos de Classes", 80, Nivel.INTERMEDIARIO))
        this.add(ConteudoEducacional("Funções de Escopo", 40, Nivel.INTERMEDIARIO))
        this.add(ConteudoEducacional("Tratamento de Exceções", 40, Nivel.INTERMEDIARIO))
        this.add(ConteudoEducacional("Manipulando API's", 160, Nivel.AVANÇADO))
    };

    //FORMAÇÃO
    val formacao: Formacao = Formacao("Kotlin Developer", conteudoEducacional, Nivel.BASICO);

    //TESTES
    formacao.matricular(usuarioFabio, usuarioFelipe);

    formacao.mostrarConteudos();
    formacao.mostrarInscritos();
}
