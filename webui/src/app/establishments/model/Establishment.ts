import { EstablishmentAttachments } from "./EstablishmentAttachments";

export class Establishment {
    id: number;
    name: string;
    category: string;
    cnpj: string;
    cpf: string;
    address: string;
    number: string;
    city: string;
    cep: string;
    responsible: string;
    observation: string;
    activity: string;
    telephone: string;
    establishmentAttachments: EstablishmentAttachments[];

    constructor () {

        this.establishmentAttachments = Array<EstablishmentAttachments>();
    }
}
