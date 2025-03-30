import type Pessoa from "./Pessoa";
import type Projeto from "./Projeto";
import StatusTarefa from "./StatusTarefa";

type TarefaParams = {
	id?: number,
	titulo?: string,
	descricao?: string,
	status?: StatusTarefa,
	pessoaResponsavel?: Pessoa,
	projeto?: Projeto,
}

export default class Tarefa {
	public id: number;
	public titulo: string;
	public descricao: string;
	public status: StatusTarefa;
	public pessoaResponsavel: Pessoa | null;
	public projeto: Projeto | null;

	constructor({ id, titulo, descricao, status, pessoaResponsavel, projeto }: TarefaParams) {
		this.id = id || 0;
		this.titulo = titulo || 'Sem título';
		this.descricao = descricao || 'Sem descrição';
		this.status = status || new StatusTarefa({});
		this.pessoaResponsavel = pessoaResponsavel || null;
		this.projeto = projeto || null;
	}
}