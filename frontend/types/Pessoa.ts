import type Equipe from "./Equipe";

type PessoaParams = {
	id?: number
	nome?: string
	equipe?: Equipe
}

export default class Pessoa {
	public id: number;
	public nome: string;
	public equipe: Equipe | null;

	constructor({ id, nome, equipe }: PessoaParams
	) {
		this.id = id || 0
		this.nome = nome || 'Sem nome'
		this.equipe = equipe || null
	}
}