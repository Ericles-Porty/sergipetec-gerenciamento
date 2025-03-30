type StatusTarefaParams = {
	id?: number
	nome?: string
}

export default class StatusTarefa {
	public id: number;
	public nome: string;

	constructor({ id, nome }: StatusTarefaParams) {
		this.id = id || 0
		this.nome = nome || 'Sem nome'
	}
}