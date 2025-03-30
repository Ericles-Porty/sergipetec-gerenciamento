import type Equipe from "./Equipe"

type ProjetoParams = {
	id?: number
	nome?: string
	descricao?: string
	dataInicio?: string
	dataFim?: string
	equipe?: Equipe
	status?: string
}

export default class Projeto {
	public id: number;
	public nome: string;
	public descricao: string;
	public dataInicio: string;
	public dataFim: string;
	public equipe: Equipe | null;
	public status: string;

	constructor({ id, nome, descricao, dataInicio, dataFim, equipe, status }: ProjetoParams
	) {
		this.id = id || 0
		this.nome = nome || 'Sem nome'
		this.descricao = descricao || 'Sem descrição'
		this.dataInicio = dataInicio || ''
		this.dataFim = dataFim || ''
		this.equipe = equipe || null
		this.status = status || 'Sem status'
	}
}