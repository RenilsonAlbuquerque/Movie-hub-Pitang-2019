import { Genere } from './genere';

export interface Serie{
    id: number,
    title: String,
    description: String,
    country: String,
    language: String
    releaseYear: number,
    durationInMinutes: number,
    voteAverage: number,
    voteCount: number,
    backdropPath: String
    generes: Genere[]
}