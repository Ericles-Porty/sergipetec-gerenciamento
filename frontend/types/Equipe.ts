import type Pessoa from "./Pessoa"
import type Projeto from "./Projeto"

type EquipeParams = {
	id?: number
	nome?: string
	pessoas?: Pessoa[]
	projetos?: Projeto[]
}

export default class Equipe {
	public id: number;
	public nome: string;
	public pessoas: Pessoa[];
	public projetos: Projeto[];

	constructor({ id, nome, pessoas, projetos }: EquipeParams
	) {
		this.id = id || 0
		this.nome = nome || 'Sem nome'
		this.pessoas = pessoas || []
		this.projetos = projetos || []
	}
}