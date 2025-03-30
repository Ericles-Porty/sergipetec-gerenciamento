import type { ApiResponseWrapper } from "~/types/ApiResponseWrapper"
import Equipe from "~/types/Equipe"
import type Projeto from "~/types/Projeto"

type ProjetosResponse = {
	id: number
	nome: string
	descricao: string
	dataInicio: string
	dataFim: string
	equipeResponsavel: string
	status: string
}

export default defineEventHandler(async (event) => {
	const runtimeConfig = useRuntimeConfig(event)
	const endpoint = runtimeConfig.public.apiBaseUrl + '/api/projetos'

	const response = await $fetch<ApiResponseWrapper<ProjetosResponse[]>>(endpoint)

	if (response.code !== 200 || !response.data) {
		return response as ApiResponseWrapper<object>
	}

	const projetos = mapProjetosResponseToProjetos(response.data as ProjetosResponse[])

	return {
		success: response.success,
		code: response.code,
		data: projetos,
		message: response.message,
		errors: response.errors
	} as ApiResponseWrapper<Projeto[]>
})

function mapProjetosResponseToProjetos(responses: ProjetosResponse[]): Projeto[] {
	return responses.map((projeto) => {
		return {
			id: projeto.id,
			nome: projeto.nome,
			descricao: projeto.descricao,
			dataInicio: projeto.dataInicio,
			dataFim: projeto.dataFim,
			equipe: new Equipe({ nome: projeto.equipeResponsavel }),
			status: projeto.status
		}
	})
}