type StatusProjetoParams = {
	id?: number
	nome?: string
}

export default class StatusProjeto {
	public id?: number;
	public nome?: string;

	constructor({ id, nome }: StatusProjetoParams) {
		this.id = id || 0
		this.nome = nome || 'Sem nome'
	}
}