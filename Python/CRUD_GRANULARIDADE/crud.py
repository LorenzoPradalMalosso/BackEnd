import json

# Menu Principal
def menu():
    print("\n === MENU DE CONTATOS ===")
    print("1. Adicionar")
    print("2. Listar")
    print("3. Atualizar")
    print("4. Excluir")
    print("0. Sair")

# Escolher grupo
def escolher_grupo():
    print("\nTipo de contato: ")
    print("1. Aluno")
    print("2. Professor")

    opcao = input("Escolha: ")

    if opcao == "1":
        return "alunos"
    
    if opcao == "2":
        return "professores"
    
    else:
        print("Opção inválida!")
        return
    
# Ler dados
def ler_dados():
    with open("contatos.json", "r", encoding="utf-8") as arquivo:
        return json.load(arquivo)

# Escrever dados
def salvar_dados(dados):
    with open("contatos.json", "w", encoding="utf-8") as arquivo:
        json.dump(dados, arquivo, indent=2, ensure_ascii=False)

def adicionar():
    grupo = escolher_grupo()

    if not grupo:
        return
    
    nome = input("Nome: ")
    telefone = input("Telefone: ")

    dados = ler_dados()
    dados[grupo].append({
        "nome": nome,
        "telefone": telefone
    })

    salvar_dados(dados)
    print("Contato adicionado com sucesso!")

def listar():
    grupo = escolher_grupo()

    if not grupo:
        return
    
    dados = ler_dados()
    print(f"\nLista de {grupo.capitalize()}: ")

    # Percorre a lista (Matriz)
    for index, contato in enumerate(dados[grupo], start=1):
        print(f"{index}. {contato["nome"]} - {contato["telefone"]}")

def atualizar():
    grupo = escolher_grupo()

    if not grupo:
        return

    dados = ler_dados()
    index = int(input("Número do index: ")) -1

    # Verificar se o index está na lista
    if 0 <= index < len(dados[grupo]):
        nome = input("Novo nome: ")
        telefone = int(input("Novo telefone: "))

        dados[grupo][index] = {
            "nome": nome,
            "telefone": telefone
        }
        salvar_dados(dados)
        print("Contato atualizado!")

    else:
        print("Índice inválido")

def excluir():
    grupo = escolher_grupo()

    if not grupo:
        return
    dados = ler_dados()

    index = int(input("Index do contato: ")) -1

    if 0 <= index < len(dados[grupo]):
        dados[grupo].pop(index)

        salvar_dados(dados)
        print("Contato excluído!")
    else:
        print("Índice inválido")

def main():
    while True:
        menu()
        opcao = int(input("Escolha uma opção: "))

        if opcao == 1:
            adicionar()

        elif opcao == 2:
            listar()

        elif opcao == 3:
            atualizar()

        elif opcao == 4:
            excluir()

        elif opcao == 0:
            print("Encerrando o programa...")
            break

        else:
            print("Opção inválida")

main()